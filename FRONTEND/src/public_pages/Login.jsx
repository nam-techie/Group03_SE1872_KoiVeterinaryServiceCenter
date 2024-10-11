import { useLogin } from '../hooks/useLogin.js';
import '../styles/Login.css';

function Login() {
  const {
    username,
    password,
    loading,
    error,
    setUsername,
    setPassword,
    handleSubmit
  } = useLogin();

  return (
      <div className="login-container">
        <form className="login-form" onSubmit={handleSubmit}>
          <h2>Đăng nhập</h2>

          {error && <p className="error-message">{error}</p>} {/* Hiển thị lỗi nếu có */}

          <div className="form-group">
            <label>Tên Đăng Nhập: </label>
            <input
                type="text"
                placeholder="Tên Đăng Nhập"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
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
            <a href="/forgot-password" className="forgot-password">Quên mật khẩu?</a>
          </div>

          <button type="submit" className="login-button" disabled={loading}>
            {loading ? 'Đang xử lý...' : 'Đăng nhập'}
          </button>

          <div className="register">
            <span>Bạn chưa có tài khoản? </span>
            <a href="/register">Đăng ký</a>
          </div>

          <div className="or-login-with">
            <span>Hoặc đăng nhập</span>
          </div>

          {/* Google Login Button */}
          <div id="signInDiv" className="google-login">
            {/* Google button will be rendered here by the SDK */}
          </div>
        </form>
      </div>
  );
}

export default Login;
