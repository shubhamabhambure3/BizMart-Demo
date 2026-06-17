import { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'

import {
  createInterest,
  getAllInterests,
  updateInterest,
  deleteInterest
} from '../services/interestService'

import {
  getAllBuyerProfiles
} from '../services/buyerService'

import {
  getAllListings
} from '../services/listingService'

import {
  getUserRole
} from '../utils/jwtUtils'

import { formatCurrency }
  from '../utils/currencyUtils'

function InterestPage() {

  const role = getUserRole()

  const [buyerProfiles,
    setBuyerProfiles] =
    useState([])

  const [listings,
    setListings] =
    useState([])

  const [interests,
    setInterests] =
    useState([])

  const [buyerProfileId,
    setBuyerProfileId] =
    useState('')

  const [listingId,
    setListingId] =
    useState('')

  const [message,
    setMessage] =
    useState('')

  const [status,
    setStatus] =
    useState('PENDING')

  const [editingId,
    setEditingId] =
    useState(null)

  const [success,
    setSuccess] =
    useState('')

  const [error,
    setError] =
    useState('')

  const loadData =
    async () => {

      try {

        const buyerData =
          await getAllBuyerProfiles()

        const listingData =
          await getAllListings()

        const interestData =
          await getAllInterests()

        setBuyerProfiles(
          buyerData
        )

        setListings(
          listingData
        )

        setInterests(
          interestData
        )

      } catch (err) {

        console.error(err)
      }
    }

  useEffect(() => {

    loadData()

  }, [])

  const clearForm = () => {

    setBuyerProfileId('')
    setListingId('')
    setMessage('')
    setStatus('PENDING')
    setEditingId(null)
  }

  const handleSave =
    async () => {

      try {

        const payload = {

          buyerProfileId:
            Number(
              buyerProfileId
            ),

          listingId:
            Number(
              listingId
            ),

          message,

          status
        }

        if (editingId) {

          await updateInterest(
            editingId,
            payload
          )

          setSuccess(
            'Interest updated successfully'
          )

        } else {

          await createInterest(
            payload
          )

          setSuccess(
            'Interest created successfully'
          )
        }

        setError('')

        clearForm()

        loadData()

      } catch (err) {

        setSuccess('')

        setError(
          err.response?.data?.message ||
          'Operation failed'
        )
      }
    }

  const handleDelete =
    async (id) => {

      if (
        !window.confirm(
          'Delete interest?'
        )
      ) {
        return
      }

      try {

        await deleteInterest(id)

        setSuccess(
          'Interest deleted successfully'
        )

        setError('')

        loadData()

      } catch (err) {

        setSuccess('')

        setError(
          err.response?.data?.message ||
          'Delete failed'
        )
      }
    }

  const handleEdit =
    (interest) => {

      setEditingId(
        interest.id
      )

      setBuyerProfileId(
        interest.buyerProfileId
      )

      setListingId(
        interest.listingId
      )

      setMessage(
        interest.message
      )

      setStatus(
        interest.status
      )

      window.scrollTo({
        top: 0,
        behavior: 'smooth'
      })
    }

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <h1 className="page-title">Interests</h1>

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

        {role === 'BUYER' && (

          <div className="card shadow-sm">

            <div className="card-body">

              <h5>
                Create Interest
              </h5>

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
                        {profile.id}
                      </option>
                    )
                  )}
                </select>

              </div>

              <div className="mb-3">

                <label className="form-label">
                  Listing
                </label>

                <select
                  className="form-select"
                  value={listingId}
                  onChange={(e) =>
                    setListingId(
                      e.target.value
                    )
                  }
                >
                  <option value="">
                    Select Listing
                  </option>

                  {listings.map(
                    listing => (
                      <option
                        key={listing.id}
                        value={listing.id}
                      >
                        Listing #{listing.id}
                      </option>
                    )
                  )}
                </select>

              </div>

              <div className="mb-3">

                <textarea
                  className="form-control"
                  placeholder="Message"
                  value={message}
                  onChange={(e) =>
                    setMessage(
                      e.target.value
                    )
                  }
                />

              </div>

              <div className="mb-3">

                <select
                  className="form-select"
                  value={status}
                  onChange={(e) =>
                    setStatus(
                      e.target.value
                    )
                  }
                >
                  <option value="PENDING">
                    PENDING
                  </option>

                  <option value="CONTACTED">
                    CONTACTED
                  </option>

                  <option value="CLOSED">
                    CLOSED
                  </option>

                </select>

              </div>

              <button
                className="btn btn-primary"
                onClick={handleSave}
              >
                {editingId
                  ? 'Update Interest'
                  : 'Create Interest'}
              </button>

            </div>

          </div>

        )}

        <div className="card mt-4 shadow-sm">

          <div className="card-body">

            <table
              className="table table-striped"
            >

              <thead className="table-dark">

                <tr>

                  <th>ID</th>

                  <th>Buyer Profile</th>

                  <th>Business Listing</th>

                  <th>Message</th>

                  <th>Interest Status</th>

                  <th>Created</th>

                  {role === 'BUYER' &&
                    <th>Actions</th>}

                </tr>

              </thead>

              <tbody>

                {interests.map(
                  interest => (

                    <tr
                      key={interest.id}
                    >

                      <td>
                        {interest.id}
                      </td>

                      <td>
                        {interest.buyerProfileId}
                      </td>

                      <td>
                        {interest.listingId}
                      </td>

                      <td>
                        {interest.message}
                      </td>

                      <td>
                        {interest.status}
                      </td>

                      <td>
                        {interest.createdAt}
                      </td>

                      {role === 'BUYER' && (

                        <td>

                          <button
                            className="btn btn-sm btn-warning me-2"
                            onClick={() =>
                              handleEdit(
                                interest
                              )
                            }
                          >
                            Edit
                          </button>

                          <button
                            className="btn btn-sm btn-danger"
                            onClick={() =>
                              handleDelete(
                                interest.id
                              )
                            }
                          >
                            Delete
                          </button>

                        </td>

                      )}

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

export default InterestPage