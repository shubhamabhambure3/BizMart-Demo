import { getToken } from '../services/authService'

export const isAuthenticated = () => {
  return !!getToken()
}