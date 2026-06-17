import { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'

import {
  createBuyerProfile,
  getAllBuyerProfiles,
  updateBuyerProfile,
  deleteBuyerProfile
} from '../services/buyerService'

import { formatCurrency }
  from '../utils/currencyUtils'

function BuyerPage() {

  const [profiles, setProfiles] =
    useState([])

  const [investmentBudget,
    setInvestmentBudget] =
    useState('')

  const [preferredIndustry,
    setPreferredIndustry] =
    useState('')

  const [location,
    setLocation] =
    useState('')

  const [editingId,
    setEditingId] =
    useState(null)

  const [success,
    setSuccess] =
    useState('')

  const [error,
    setError] =
    useState('')

  const loadProfiles =
    async () => {

      try {

        const data =
          await getAllBuyerProfiles()

        setProfiles(data)

      } catch (err) {

        console.error(err)
      }
    }

  useEffect(() => {

    loadProfiles()

  }, [])

  const clearForm = () => {

    setInvestmentBudget('')
    setPreferredIndustry('')
    setLocation('')
    setEditingId(null)
  }

  const handleSave =
    async () => {

      try {

        const payload = {

          investmentBudget,

          preferredIndustry,

          location
        }

        if (editingId) {

          await updateBuyerProfile(
            editingId,
            payload
          )

          setSuccess(
            'Buyer profile updated successfully'
          )

        } else {

          await createBuyerProfile(
            payload
          )

          setSuccess(
            'Buyer profile created successfully'
          )
        }

        setError('')

        clearForm()

        loadProfiles()

      } catch (err) {

        setSuccess('')

        setError(
          err.response?.data?.message ||
          'Operation failed'
        )
      }
    }

  const handleEdit =
    (profile) => {

      setEditingId(profile.id)

      setInvestmentBudget(
        profile.investmentBudget
      )

      setPreferredIndustry(
        profile.preferredIndustry
      )

      setLocation(
        profile.location
      )

      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      })
    }

  const handleDelete =
    async (id) => {

      if (
        !window.confirm(
          'Delete buyer profile?'
        )
      ) {
        return
      }

      try {

        await deleteBuyerProfile(id)

        setSuccess(
          'Buyer profile deleted successfully'
        )

        setError('')

        loadProfiles()

      } catch (err) {

        setSuccess('')

        setError(
          err.response?.data?.message ||
          'Delete failed'
        )
      }
    }

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <h1 className="page-title">Buyer Profiles</h1>

        {success && (
          <div className="alert alert-success">
            {success}
          </div>
        )}

        {error && (
          <div className="alert alert-danger">
            {error}
          </div>
        )}

        <div className="card shadow-sm">

          <div className="card-body">

            <h5>
              Buyer Profile
            </h5>

            <div className="mb-3">

              <label className="form-label">
                Investment Budget
              </label>

              <input
                type="number"
                className="form-control"
                value={investmentBudget}
                onChange={(e) =>
                  setInvestmentBudget(
                    e.target.value
                  )
                }
              />

            </div>

            <div className="mb-3">

              <label className="form-label">
                Preferred Industry
              </label>

              <input
                type="text"
                className="form-control"
                value={preferredIndustry}
                onChange={(e) =>
                  setPreferredIndustry(
                    e.target.value
                  )
                }
              />

            </div>

            <div className="mb-3">

              <label className="form-label">
                Location
              </label>

              <input
                type="text"
                className="form-control"
                value={location}
                onChange={(e) =>
                  setLocation(
                    e.target.value
                  )
                }
              />

            </div>

            <button
              className="btn btn-primary"
              onClick={handleSave}
            >
              {editingId
                ? 'Update Profile'
                : 'Create Profile'}
            </button>

          </div>

        </div>

        <div className="card mt-4 shadow-sm">

          <div className="card-body">

            <h5 className="page-title">
              Buyer Profiles
            </h5>

            <table
              className="table table-striped"
            >

              <thead className="table-dark">

                <tr>

                  <th>Profile ID</th>

                  <th>Budget</th>

                  <th>Industry</th>

                  <th>Location</th>

                  <th>Actions</th>

                </tr>

              </thead>

              <tbody>

                {profiles.map(
                  (profile) => (

                    <tr
                      key={profile.id}
                    >

                      <td>
                        {profile.id}
                      </td>

                      <td>
                        {formatCurrency(profile.investmentBudget)}
                      </td>

                      <td>
                        {profile.preferredIndustry}
                      </td>

                      <td>
                        {profile.location}
                      </td>

                      <td>

                        <button
                          className="btn btn-sm btn-warning me-2"
                          onClick={() =>
                            handleEdit(
                              profile
                            )
                          }
                        >
                          Edit
                        </button>

                        <button
                          className="btn btn-sm btn-danger"
                          onClick={() =>
                            handleDelete(
                              profile.id
                            )
                          }
                        >
                          Delete
                        </button>

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

export default BuyerPage