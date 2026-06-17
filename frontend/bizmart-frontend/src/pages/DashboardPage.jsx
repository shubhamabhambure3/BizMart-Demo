import { useNavigate } from 'react-router-dom'
import { logout } from '../services/authService'
import Navbar from '../components/Navbar'
import logo from '../assets/bizmart-logo.png'

function DashboardPage() {
  const navigate = useNavigate()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <div className="text-center mb-3">

          <img
            src={logo}
            alt="BizMart"
            width="140"
            className="mb-3"
          />

          <p className="text-muted">
            Buy • Sell • Match Businesses
          </p>

        </div>

        <div className="row">

          <div className="col-md-4 mb-4">
            <div
              className="card shadow-sm h-100"
              style={{
                cursor: 'pointer'
              }}
              onClick={() =>
                navigate('/companies')
              }
            >
              <div className="card-body text-center">
                <h1>🏢</h1>

                <h4 className="mt-3">
                  Companies
                </h4>

                <p className="text-muted">
                  Create and manage
                  business profiles
                </p>
                {/* 
                <button
                  className="btn btn-sm btn-primary mt-2"
                >
                  Open
                </button> */}
              </div>
            </div>
          </div>

          <div className="col-md-4 mb-4">
            <div
              className="card shadow-sm h-100 dashboard-card"
              style={{
                cursor: 'pointer'
              }}
              onClick={() =>
                navigate('/listings')
              }
            >
              <div className="card-body text-center">
                <h1>📢</h1>

                <h4 className="mt-3">
                  Listings
                </h4>

                <p className="text-muted">
                  Manage acquisition
                  opportunities
                </p>
              </div>
            </div>
          </div>

          <div className="col-md-4 mb-4">
            <div
              className="card shadow-sm h-100"
              style={{
                cursor: 'pointer'
              }}
              onClick={() =>
                navigate('/matching')
              }
            >
              <div className="card-body text-center">
                <h1>🎯</h1>

                <h4 className="mt-3">
                  Matching Engine
                </h4>

                <p className="text-muted">
                  Discover buyer and
                  seller opportunities
                </p>
              </div>
            </div>
          </div>

        </div>

        <div className="row">

          <div className="col-md-4 mb-4">
            <div
              className="card shadow-sm h-100"
              style={{
                cursor: 'pointer'
              }}
              onClick={() =>
                navigate('/buyers')
              }
            >
              <div className="card-body text-center">
                <h1>👤</h1>

                <h4 className="mt-3">
                  Buyer Profiles
                </h4>

                <p className="text-muted">
                  Manage investor
                  preferences
                </p>
              </div>
            </div>
          </div>

          <div className="col-md-4 mb-4">
            <div
              className="card shadow-sm h-100"
              style={{
                cursor: 'pointer'
              }}
              onClick={() =>
                navigate('/interests')
              }
            >
              <div className="card-body text-center">
                <h1>🤝</h1>

                <h4 className="mt-3">
                  Interests
                </h4>

                <p className="text-muted">
                  Track acquisition
                  requests
                </p>
              </div>
            </div>
          </div>

          <div className="col-md-4 mb-4">
            <div
              className="card shadow-sm h-100"
              style={{
                cursor: 'pointer'
              }}
              onClick={() =>
                navigate('/contacts')
              }
            >
              <div className="card-body text-center">
                <h1>📞</h1>

                <h4 className="mt-3">
                  Contacts
                </h4>

                <p className="text-muted">
                  Manage buyer-seller
                  communication
                </p>
              </div>
            </div>
          </div>

        </div>

      </div>
    </>
  )
}

export default DashboardPage