import { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import { register } from '../services/registerService'
import logo from '../assets/bizmart-logo.png'

function RegisterPage() {
  const [fullName, setFullName] = useState('')
  const [email, setEmail] = useState('')
  const [mobile, setMobile] = useState('')
  const [password, setPassword] = useState('')
  const [role, setRole] = useState('BUYER')
  const [error, setError] = useState('')
  const [success, setSuccess] = useState('')

  const navigate = useNavigate()

  const handleSubmit = async (event) => {
    event.preventDefault()

    try {
      await register({
        fullName,
        email,
        mobile,
        password,
        role
      })

      setSuccess('Registration successful')

      setTimeout(() => {
        navigate('/login')
      }, 1500)

    } catch (err) {
      setError('Registration failed')
    }
  }

  return (
    <div className="container mt-5">
      <div className="row justify-content-center">

        <div className="col-md-6">

          <div className="card shadow">

            <div className="card-body">

              <div className="text-center mb-4">

                <img
                  src={logo}
                  alt="BizMart"
                  width="120"
                  className="mb-3"
                />

                <h2>
                  Register
                </h2>

                <p className="text-muted">
                  Buy • Sell • Match Businesses
                </p>

              </div>

              {error && (
                <div className="alert alert-danger">
                  {error}
                </div>
              )}

              {success && (
                <div className="alert alert-success">
                  {success}
                </div>
              )}

              <form onSubmit={handleSubmit}>

                <div className="mb-3">
                  <label className="form-label">
                    Full Name
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    value={fullName}
                    onChange={(e) =>
                      setFullName(e.target.value)
                    }
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">
                    Email
                  </label>

                  <input
                    type="email"
                    className="form-control"
                    value={email}
                    onChange={(e) =>
                      setEmail(e.target.value)
                    }
                  />
                </div>

                <div className="mb-3">
                  <label className="form-label">
                    Mobile
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    value={mobile}
                    onChange={(e) =>
                      setMobile(e.target.value)
                    }
                  />
                </div>

                <div className="mb-3">
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
                  />
                </div>

                <div className="mb-4">
                  <label className="form-label">
                    Role
                  </label>

                  <select
                    className="form-select"
                    value={role}
                    onChange={(e) =>
                      setRole(e.target.value)
                    }
                  >
                    <option value="BUYER">
                      Buyer
                    </option>

                    <option value="SELLER">
                      Seller
                    </option>
                  </select>
                </div>

                <button
                  type="submit"
                  className="btn btn-primary w-100"
                >
                  Register
                </button>

                <div className="text-center mt-3">

                  <span className="text-muted">
                    Already have an account?
                  </span>

                  <button
                    type="button"
                    className="btn btn-link"
                    onClick={() => navigate('/login')}
                  >
                    Login
                  </button>

                </div>

              </form>

            </div>

          </div>

        </div>

      </div>
    </div>
  )
}

export default RegisterPage