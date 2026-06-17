import { useNavigate } from 'react-router-dom'
import { logout } from '../services/authService'
import logo from '../assets/bizmart-logo.png'

function Navbar() {
  const navigate = useNavigate()

  const handleLogout = () => {
    logout()
    navigate('/login')
  }

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

      <div className="container-fluid">

        <div
          className="navbar-brand d-flex align-items-center"
          style={{ cursor: 'pointer' }}
          onClick={() => navigate('/dashboard')}
        >
          <img
            src={logo}
            alt="BizMart"
            width="40"
            height="40"
            className="me-2"
          />
          <span>BizMart</span>
        </div>

        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarNav"
          aria-controls="navbarNav"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div
          className="collapse navbar-collapse"
          id="navbarNav"
        >

          <div className="navbar-nav me-auto">

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/dashboard')}
            >
              Dashboard
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/companies')}
            >
              Companies
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/financials')}
            >
              Financials
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/valuations')}
            >
              Valuations
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/listings')}
            >
              Listings
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/buyers')}
            >
              Buyer Profile
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/interests')}
            >
              Interests
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/matching')}
            >
              Matching
            </button>

            <button
              className="nav-link btn btn-link text-white"
              onClick={() => navigate('/contacts')}
            >
              Contacts
            </button>

          </div>

          <button
            className="btn btn-outline-light"
            onClick={handleLogout}
          >
            Logout
          </button>

        </div>

      </div>

    </nav>
  )
}

export default Navbar