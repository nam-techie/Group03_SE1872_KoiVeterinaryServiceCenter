package com.namtechie.org.service;

import com.namtechie.org.entity.Account;
import com.namtechie.org.entity.Role;
import com.namtechie.org.exception.DuplicateEntity;
import com.namtechie.org.exception.EntityNotFoundException;
import com.namtechie.org.model.AccountResponse;
import com.namtechie.org.model.VeterinaryRequest;
import com.namtechie.org.repository.AccountRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class AdminService implements UserDetailsService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenService tokenService;


    private String generateUsername() {
        // Tìm kiếm số lượng bác sĩ hiện có để tạo username tiếp theo
        List<Account> veterinaryAccounts = accountRepository.findByRoleIgnoreCase(Role.VETERINARY.name());
        int nextNumber = veterinaryAccounts.size() + 1;
        String generatedUsername = "veterinary" + String.format("%02d", nextNumber);

        // Kiểm tra xem username có tồn tại không để tránh trùng lặp
        while (accountRepository.existsByUsername(generatedUsername)) {
            nextNumber++;
            generatedUsername = "veterinary" + String.format("%02d", nextNumber);
        }
        return generatedUsername;
    }


    public AccountResponse registerVeterinary(VeterinaryRequest veterinaryRequest) {
        Account account = modelMapper.map(veterinaryRequest, Account.class);

        try {
            // Mật khẩu mặc định cho bác sĩ
            String generatedUsername = generateUsername();
            // Tạo account mới

            account.setEmail(veterinaryRequest.getEmail());
            account.setUsername(generatedUsername);
            account.setPassword(passwordEncoder.encode("123456"));
            account.setRole(Role.VETERINARY.name());

            // Lưu tài khoản bác sĩ vào database
            Account newAccount = accountRepository.save(account);

            // Chuyển đổi thành AccountResponse và trả về
            return modelMapper.map(newAccount, AccountResponse.class);
        } catch (Exception e) {
            if (e.getMessage().contains(account.getEmail())) {
                throw new DuplicateEntity("Email này đã được sử dụng!");
            }
            throw new RuntimeException("Đã xảy ra lỗi trong quá trình đăng ký, vui lòng thử lại sau.");
        }
    }


    public AccountResponse setVeterinaryAccount(String email) {
        // Tìm tài khoản của bác sĩ thú y dựa trên email
        Account veterinaryAccount = accountRepository.findAccountByEmail(email);

        // Kiểm tra nếu tài khoản không tồn tại
        if (veterinaryAccount == null) {
            throw new EntityNotFoundException("Không tìm thấy tài khoản với email: " + email);
        }

        // Đặt role VETERINARY
        veterinaryAccount.setRole(Role.VETERINARY.name());

        // Lưu vào cơ sở dữ liệu
        Account updatedAccount = accountRepository.save(veterinaryAccount);

        // Chuyển đổi từ Account sang AccountResponse
        AccountResponse accountResponse = modelMapper.map(updatedAccount, AccountResponse.class);
        return accountResponse;
    }

    public List<Account> getAllAccount() {
        List<Account> accounts = accountRepository.findAccountByIsDeletedFalse();
        return accounts;
    }

    public void deleteAccount(String username) {
        // Kiểm tra xem tài khoản có tồn tại hay không trước khi xóa
        if (accountRepository.existsByUsername(username)) {
            accountRepository.updateIsDeletedByUsername(true, username);
        } else {
            throw new EntityNotFoundException("Tài khoản với username: " + username + " không tồn tại.");
        }
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findAccountByUsername(username);
    }


}

