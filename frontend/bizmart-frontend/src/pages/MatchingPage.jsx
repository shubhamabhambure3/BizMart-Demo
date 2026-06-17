import { useEffect, useState } from 'react'

import Navbar from '../components/Navbar'

import {
  getAllBuyerProfiles
} from '../services/buyerService'

import {
  getMatchingListings
} from '../services/matchingService'

import {
  getUserRole
} from '../utils/jwtUtils'

import { formatCurrency }
  from '../utils/currencyUtils'

import {
  getAllCompanies
} from '../services/companyService'



function MatchingPage() {

  const role =
    getUserRole()

  const [buyerProfiles,
    setBuyerProfiles] =
    useState([])

  const [buyerProfileId,
    setBuyerProfileId] =
    useState('')

  const [matches,
    setMatches] =
    useState([])

  const [error,
    setError] =
    useState('')

  const [companies, setCompanies] =
    useState([])

  const getCompanyName =
    (companyId) => {

      const company =
        companies.find(
          c => c.id === companyId
        )

      return company
        ? company.companyName
        : `Company #${companyId}`
    }


  useEffect(() => {

    loadBuyerProfiles()
    getCompanyName()
  }, [])

  const loadBuyerProfiles =
    async () => {

      try {

        const data =
          await getAllBuyerProfiles()

        setBuyerProfiles(data)

      } catch (err) {

        console.error(err)
      }
    }

  const handleFindMatches =
    async () => {

      try {

        const data =
          await getMatchingListings(
            buyerProfileId
          )

        setMatches(data)

        setError('')

      } catch (err) {

        setError(
          err.response?.data?.message ||
          'Failed to load matches'
        )
      }
    }

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <h1 className="page-title">
          Matching Engine
        </h1>

        {role !== 'BUYER' && (

          <div className="alert alert-info">

            Matching is available
            for buyers only.

          </div>

        )}

        {role === 'BUYER' && (

          <div className="card shadow-sm">

            <div className="card-body">

              <div className="mb-3">

                <label className="form-label">
                  Buyer Profile
                </label>

                <select
                  className="form-select"
                  value={buyerProfileId}
                  onChange={(e) =>
                    setBuyerProfileId(
                      e.target.value
                    )
                  }
                >

                  <option value="">
                    Select Buyer Profile
                  </option>

                  {buyerProfiles.map(
                    profile => (

                      <option
                        key={profile.id}
                        value={profile.id}
                      >
                        Profile #{profile.id}
                      </option>
                    )
                  )}

                </select>

              </div>

              <button
                className="btn btn-primary"
                onClick={
                  handleFindMatches
                }
              >
                Find Matches
              </button>

            </div>

          </div>

        )}

        {error && (

          <div className="alert alert-danger mt-3">

            {error}

          </div>

        )}

        <div className="card mt-4">

          <div className="card-body">

            <table
              className="table table-striped"
            >

              <thead className="table-dark">

                <tr>

                  <th>ID</th>

                  <th>Company Name</th>

                  <th>Business Valuation</th>

                  <th>Asking Price</th>

                  <th>Status</th>

                </tr>

              </thead>

              <tbody>

                {matches.map(
                  listing => (

                    <tr
                      key={listing.id}
                    >

                      <td>
                        {listing.id}
                      </td>

                      <td>
                        {getCompanyName(listing.companyId)}
                      </td>

                      <td>
                        {listing.valuationId}
                      </td>

                      <td>
                        {formatCurrency(listing.askingPrice)}
                      </td>

                      <td>
                        {listing.status}
                      </td>

                    </tr>

                  )
                )}

              </tbody>

            </table>

          </div>

        </div>

      </div>

    </>
  )
}

export default MatchingPage