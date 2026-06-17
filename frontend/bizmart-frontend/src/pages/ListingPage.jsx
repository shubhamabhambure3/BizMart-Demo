import { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'

import {
  createListing,
  getAllListings,
  updateListing,
  deleteListing
} from '../services/listingService'

import {
  getAllCompanies
} from '../services/companyService'

import {
  getUserRole
} from '../utils/jwtUtils'

import { formatCurrency }
  from '../utils/currencyUtils'

import { getAllValuations } from '../services/valuationService'

function ListingPage() {

  const role = getUserRole()

  const [companies, setCompanies] =
    useState([])

  const [listings, setListings] =
    useState([])

  const [companyId, setCompanyId] =
    useState('')

  const [valuationId, setValuationId] =
    useState('')

  const [askingPrice, setAskingPrice] =
    useState('')

  const [status, setStatus] =
    useState('ACTIVE')

  const [editingId, setEditingId] =
    useState(null)

  const [success, setSuccess] =
    useState('')

  const [error, setError] =
    useState('')

  const [valuations, setValuations] =
    useState([])

  const loadValuations =
    async () => {

      const data =
        await getAllValuations()

      setValuations(data)
    }

  const loadCompanies =
    async () => {

      const data =
        await getAllCompanies()

      setCompanies(data)
    }

  const loadData = async () => {

    try {

      const companyData =
        await getAllCompanies()

      const listingData =
        await getAllListings()

      setCompanies(companyData)
      setListings(listingData)

    } catch (err) {
      console.error(err)
    }
  }

  useEffect(() => {
    loadData()
    loadValuations()
  }, [])

  const clearForm = () => {

    setCompanyId('')
    setValuationId('')
    setAskingPrice('')
    setStatus('ACTIVE')
    setEditingId(null)
  }

  const handleSave = async () => {

    const payload = {

      companyId:
        Number(companyId),

      valuationId:
        Number(valuationId),

      askingPrice,

      status
    }

    try {

      if (editingId) {

        await updateListing(
          editingId,
          payload
        )

        setSuccess(
          'Listing updated successfully'
        )

      } else {

        await createListing(
          payload
        )

        setSuccess(
          'Listing created successfully'
        )
      }

      setError('')

      clearForm()

      await loadData()

    } catch (err) {

      setSuccess('')

      setError(
        err.response?.data?.message ||
        'Operation failed'
      )
    }
  }

  const handleDelete = async (id) => {

    if (
      !window.confirm(
        'Delete listing?'
      )
    ) {
      return
    }

    try {

      await deleteListing(id)

      setSuccess(
        'Listing deleted successfully'
      )

      setError('')

      await loadData()

    } catch (err) {

      setSuccess('')

      setError(
        err.response?.data?.message ||
        'Delete failed'
      )
    }
  }

  const handleEdit = (listing) => {

    setEditingId(listing.id)

    setCompanyId(
      listing.companyId
    )

    setValuationId(
      listing.valuationId
    )

    setAskingPrice(
      listing.askingPrice
    )

    setStatus(
      listing.status
    )

    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    })
  }

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

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <h1 className="page-title">
          📢 Listings
        </h1>

        {success &&
          <div className="alert alert-success">
            {success}
          </div>
        }

        {error &&
          <div className="alert alert-danger">
            {error}
          </div>
        }

        {role === 'SELLER' && (

          <div className="card shadow-sm">

            <div className="card-body">

              <h5>Create Listing</h5>

              <div className="mb-3">

                <label className="form-label">
                  Company
                </label>

                <select
                  className="form-select"
                  value={companyId}
                  onChange={(e) =>
                    setCompanyId(
                      e.target.value
                    )
                  }
                >
                  <option value="">
                    Select Company
                  </option>

                  {companies.map(company => (
                    <option
                      key={company.id}
                      value={company.id}
                    >
                      {company.companyName}
                    </option>
                  ))}
                </select>

              </div>

              <div className="mb-3">
                <input
                  className="form-control"
                  placeholder="Valuation ID"
                  value={valuationId}
                  onChange={(e) =>
                    setValuationId(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="mb-3">
                <input
                  className="form-control"
                  placeholder="Asking Price"
                  value={askingPrice}
                  onChange={(e) =>
                    setAskingPrice(
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
                  <option value="ACTIVE">
                    ACTIVE
                  </option>

                  <option value="INACTIVE">
                    INACTIVE
                  </option>

                  <option value="SOLD">
                    SOLD
                  </option>

                </select>

              </div>

              <button
                className="btn btn-primary"
                onClick={handleSave}
              >
                {editingId
                  ? 'Update Listing'
                  : 'Create Listing'}
              </button>

            </div>

          </div>

        )}

        <div className="card mt-4 shadow-sm">

          <div className="card-body">

            <table className="table table-striped">

              <thead className="table-dark">

                <tr>

                  <th>ID</th>

                  <th>Company Name</th>

                  <th>Business Valuation</th>

                  <th>Asking Price</th>

                  <th>Listing Status</th>

                  {role === 'SELLER' &&
                    <th>Actions</th>}

                </tr>

              </thead>

              <tbody>

                {listings.map(listing => (

                  <tr key={listing.id}>

                    <td>{listing.id}</td>

                    <td>
                      {
                        getCompanyName(
                          listing.companyId
                        )
                      }
                    </td>

                    <td>
                      Valuation Ref #{listing.valuationId}
                    </td>

                    <td>
                      {formatCurrency(
                        listing.askingPrice
                      )}
                    </td>

                    <td>
                      {listing.status}
                    </td>

                    {role === 'SELLER' && (

                      <td>

                        <button
                          className="btn btn-sm btn-warning me-2"
                          onClick={() =>
                            handleEdit(
                              listing
                            )
                          }
                        >
                          Edit
                        </button>

                        <button
                          className="btn btn-sm btn-danger"
                          onClick={() =>
                            handleDelete(
                              listing.id
                            )
                          }
                        >
                          Delete
                        </button>

                      </td>

                    )}

                  </tr>

                ))}

              </tbody>

            </table>

          </div>

        </div>

      </div>
    </>
  )
}

export default ListingPage