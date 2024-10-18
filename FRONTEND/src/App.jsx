import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Navbar from "./components/Navbar.jsx"
import HomePage from './public_pages/HomePage.jsx';
import LoginPage from './public_pages/Login.jsx';
import GoogleLoginSuccess from './public_pages/GoogleLoginSuccess.jsx';
import RegisterPage from './public_pages/Register.jsx'
import ServicesPage from './public_pages/ServicesPage';
import Layout from './components/Layout.jsx';
import ContactButton from "./components/ContactButton.jsx";
import AboutMe from "./public_pages/AboutMe.jsx";
import BookingPage from "./public_pages/BookingPage.jsx"
import TermAndRefund from "./public_pages/TermAndRefund.jsx";
import ForgotPassword from "./public_pages/ForgotPassword.jsx";
import BookingServiceHistory from "./public_pages/BookingServiceHistory.jsx";
import VerifyOTP from './public_pages/VerifyOTP.jsx';
import ResetPassword from "./public_pages/ResetPassword.jsx";
import Profile from "./public_pages/Profile.jsx";
import UpdateProfile from "./public_pages/UpdateProfile.jsx"; 

import AdminDashboard from './dashboard/Dashboard.jsx';
import VeterianDashBoard from "./veterian_pages/VeterianDashBoard.jsx";



function App() {
    return (
        <Router>
            <div>
                <Navbar/> {/* Navbar sẽ hiển thị trên tất cả các trang */}
                <Layout>  {/* Bọc các Routes bên trong Layout */}
                    <Routes>
                        <Route path="/" element={<HomePage/>}/>
                        <Route path="/homepage" element={<HomePage/>}/>
                        <Route path="/login" element={<LoginPage/>}/>
                        <Route path="/login/success" element={<GoogleLoginSuccess/>}/>
                        <Route path="/services" element={<ServicesPage/>}/>
                        <Route path="/AboutMe" element={<AboutMe/>}/>
                        <Route path="/register" element={<RegisterPage/>}/>
                        <Route path="/bookingpage" element={<BookingPage/>}/>
                        <Route path="/termandrefunds" element={<TermAndRefund/>}/>
                        <Route path="/forgot-password" element={<ForgotPassword/>}/>
                        <Route path="/booking-service-history" element={<BookingServiceHistory/>}/>
                        <Route path="/verify-otp" element={<VerifyOTP />} />
                        <Route path="/reset-password" element={<ResetPassword/>}/>
                        <Route path="/profile" element={<Profile/>}/>
                        <Route path="/update-profile" element={<UpdateProfile/>}/>
                        <Route path="/dashboard" element={<AdminDashboard/>} />
                        i<Route path="/doctor-dashboard" element={<VeterianDashBoard/>}/>
                    </Routes>
                    <ContactButton/>
                </Layout>
            </div>
        </Router>
    );
}

export default App;
