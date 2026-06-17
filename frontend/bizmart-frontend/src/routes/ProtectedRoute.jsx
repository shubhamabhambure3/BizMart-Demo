import { Navigate } from 'react-router-dom'
import { isAuthenticated } from '../utils/authUtils'

function ProtectedRoute({ children }) {
  if (!isAuthenticated()) {
    return <Navigate to="/login" />
  }

  return children
}

export default ProtectedRoute