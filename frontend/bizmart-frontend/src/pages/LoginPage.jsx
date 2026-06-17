import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { login, saveToken } from '../services/authService'
import logo from '../assets/bizmart-logo.png'
import './LoginPage.css'
import { Link } from 'react-router-dom'


function LoginPage() {
  const [email, setEmail] = useState('')
  const [password, setPassword] = useState('')
  const [error, setError] = useState('')

  const navigate = useNavigate()

  const handleSubmit = async (event) => {
    event.preventDefault()

    try {
      const response = await login({
        email,
        password
      })

      saveToken(response.token)

      navigate('/dashboard')
    } catch (err) {
      setError('Invalid email or password')
    }
  }

  return (
    <div className="login-page">
      <div className="login-left">
        <img src={logo} alt="BizMart" className="brand-logo" />

        {/* <h1>Business Acquisition Marketplace</h1> */}

        <h1>
          Buy & Sell Businesses
          <br />
          with Confidence
        </h1>

        <p>
          Connect serious buyers and business owners through a
          secure platform built for acquisitions and investment.
        </p>

        <ul className="feature-list">
          <li>Secure Transactions</li>
          <li>Verified Opportunities</li>
          <li>Smart Buyer Matching</li>
        </ul>
      </div>

      <div className="login-right">
        <div className="login-card card shadow-lg">
          <div className="card-body">
            <div className="text-center mb-4">
              <img
                src={logo}
                alt="BizMart"
                className="login-logo"
              />

              <h2 className="fw-bold text-dark">Welcome Back</h2>

              <p className="text-muted mb-4">
                Sign in to continue
              </p>
            </div>

            {error && (
              <div className="alert alert-danger">
                {error}
              </div>
            )}

            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label className="form-label">
                  Email Address
                </label>

                <input
                  type="email"
                  className="form-control"
                  value={email}
                  onChange={(e) =>
                    setEmail(e.target.value)
                  }
                  required
                />
              </div>

              <div className="mb-4">
                <label className="form-label">
                  Password
                </label>

                <input
                  type="password"
                  className="form-control"
                  value={password}
                  onChange={(e) =>
                    setPassword(e.target.value)
                  }
                  required
                />
              </div>

              <button
                type="submit"
                className="btn login-btn w-100"
              >
                Login
              </button>

              <div className="text-center mt-3">
                <span className="text-muted">
                  Don't have an account?
                </span>

                <Link
                  to="/register"
                  className="ms-2 text-decoration-none"
                >
                  Register
                </Link>
              </div>

            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default LoginPage