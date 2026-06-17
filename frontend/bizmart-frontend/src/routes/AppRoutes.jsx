import { Routes, Route } from 'react-router-dom'
import LoginPage from '../pages/LoginPage'
import RegisterPage from '../pages/RegisterPage'
import DashboardPage from '../pages/DashboardPage'
import ProtectedRoute from './ProtectedRoute'
import CompaniesPage from '../pages/CompaniesPage'
import FinancialPage
  from '../pages/FinancialPage'
import ValuationPage
  from '../pages/ValuationPage'
import ListingPage from '../pages/ListingPage'
import BuyerPage
  from '../pages/BuyerPage'
import InterestPage
  from '../pages/InterestPage'
import MatchingPage
  from '../pages/MatchingPage'
import ContactPage from '../pages/ContactPage'

function AppRoutes() {
  return (
    <Routes>
      <Route path="/" element={<LoginPage />} />
      <Route path="/login" element={<LoginPage />} />
      <Route path="/register" element={<RegisterPage />} />
      <Route path="/dashboard" element={
        <ProtectedRoute>
          <DashboardPage />
        </ProtectedRoute>
      } />
      <Route
        path="/companies"
        element={
          <ProtectedRoute>
            <CompaniesPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/financials"
        element={
          <ProtectedRoute>
            <FinancialPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/valuations"
        element={
          <ProtectedRoute>
            <ValuationPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/listings"
        element={
          <ProtectedRoute>
            <ListingPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/buyers"
        element={
          <ProtectedRoute>
            <BuyerPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/interests"
        element={
          <ProtectedRoute>
            <InterestPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/matching"
        element={
          <ProtectedRoute>
            <MatchingPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/contacts"
        element={
          <ProtectedRoute>
            <ContactPage />
          </ProtectedRoute>
        }
      />
    </Routes>
  )
}

export default AppRoutes