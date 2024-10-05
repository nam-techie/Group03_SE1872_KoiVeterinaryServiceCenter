import '../styles/Register.css';
import { useRegister } from '../hooks/useRegister.js';

function Register() {
  const {
    username,
    setUsername,
    email,
    setEmail,
    password,
    setPassword,
    confirmPassword,
    setConfirmPassword,
    passwordsMatch, // Nhận trạng thái kiểm tra mật khẩu có khớp không
    loading,
    error,
    handleSubmit,
  } = useRegister();

  return (
      <div className="register-container">
        <form className="register-form" onSubmit={handleSubmit}>
          <h2>Đăng ký</h2>

          {error && <p className="error-message">{error}</p>} {/* Hiển thị lỗi nếu có */}

          <div className="form-group">
            <label>Tên Đăng Kí: </label>
            <input
                type="text"
                placeholder="Tên đăng nhập"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
            />
          </div>

          <div className="form-group">
            <label>Email: </label>
            <input
                type="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />
          </div>

          <div className="form-group">
            <label>Mật khẩu: </label>
            <input
                type="password"
                placeholder="Mật khẩu"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
            />
          </div>

          <div className="form-group">
            <label>Xác nhận mật khẩu: </label>
            <input
                type="password"
                placeholder="Xác nhận mật khẩu"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)} // Thay đổi confirmPassword khi gõ
                className={!passwordsMatch && confirmPassword ? 'error-input' : ''} // Thêm class error khi không khớp
                required
            />
            {!passwordsMatch && confirmPassword && (
                <p className="error-message">Mật khẩu không khớp</p>
            )} {/* Hiển thị lỗi nếu mật khẩu không khớp */}
          </div>

          <button type="submit" className="register-button" disabled={loading}>
            {loading ? 'Đang xử lý...' : 'Đăng ký'}
          </button>

          <div className="login">
            <span>Bạn đã có tài khoản? </span>
            <a href="/login">Đăng nhập</a>
          </div>
        </form>
      </div>
  );
}

export default Register;