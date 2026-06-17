import { useState, useEffect } from 'react'
import Navbar from '../components/Navbar'
import {
  createCompany,
  getAllCompanies,
  deleteCompany,
  updateCompany
} from '../services/companyService'

import { formatCurrency }
  from '../utils/currencyUtils'

function CompaniesPage() {

  const [companyName, setCompanyName] = useState('')
  const [industry, setIndustry] = useState('')
  const [description, setDescription] = useState('')
  const [foundedYear, setFoundedYear] = useState('')
  const [employeeCount, setEmployeeCount] = useState('')
  const [location, setLocation] = useState('')

  const [success, setSuccess] = useState('')
  const [error, setError] = useState('')

  const [companies, setCompanies] = useState([])

  const [editingId, setEditingId] =
    useState(null)

  const loadCompanies = async () => {
    try {
      const data = await getAllCompanies()
      setCompanies(data)
    } catch (err) {
      console.error(err)
    }
  }

  useEffect(() => {
    loadCompanies()
  }, [])

  const handleCreateCompany = async () => {
    try {
      await createCompany({
        companyName,
        industry,
        description,
        foundedYear: Number(foundedYear),
        employeeCount: Number(employeeCount),
        location
      })

      setSuccess(
        'Company created successfully'
      )

      setError('')

      setCompanyName('')
      setIndustry('')
      setDescription('')
      setFoundedYear('')
      setEmployeeCount('')
      setLocation('')

      await loadCompanies()

    } catch (err) {

      setError(
        err.response?.data?.message ||
        'Company creation failed'
      )

      setSuccess('')
    }
  }

  const handleDeleteCompany = async (id) => {

    const confirmed =
      window.confirm(
        'Are you sure you want to delete this company?'
      )

    if (!confirmed) {
      return
    }

    try {

      await deleteCompany(id)

      setSuccess(
        'Company deleted successfully'
      )

      setError('')

      await loadCompanies()

    } catch (err) {

      setError(
        err.response?.data?.message ||
        'Delete failed'
      )

      setSuccess('')
    }
  }

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <h1 className="page-title">
          🏢 Companies
        </h1>

        <p className="text-muted">
          Manage company information and
          marketplace visibility.
        </p>

        <div className="card shadow-sm mt-4">

          <div className="card-header">
            <h5 className="mb-0">
              Create Company
            </h5>
          </div>

          <div className="card-body">

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

            <div className="row">

              <div className="col-md-6 mb-3">
                <label className="form-label">
                  Company Name
                </label>

                <input
                  type="text"
                  className="form-control"
                  value={companyName}
                  onChange={(e) =>
                    setCompanyName(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-md-6 mb-3">
                <label className="form-label">
                  Industry
                </label>

                <input
                  type="text"
                  className="form-control"
                  value={industry}
                  onChange={(e) =>
                    setIndustry(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-12 mb-3">
                <label className="form-label">
                  Description
                </label>

                <textarea
                  rows="3"
                  className="form-control"
                  value={description}
                  onChange={(e) =>
                    setDescription(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
                <label className="form-label">
                  Founded Year
                </label>

                <input
                  type="number"
                  className="form-control"
                  value={foundedYear}
                  onChange={(e) =>
                    setFoundedYear(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
                <label className="form-label">
                  Employee Count
                </label>

                <input
                  type="number"
                  className="form-control"
                  value={employeeCount}
                  onChange={(e) =>
                    setEmployeeCount(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
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

            </div>

            <button
              type="button"
              className="btn btn-primary px-4"
              onClick={async () => {

                try {

                  if (editingId) {

                    await updateCompany(
                      editingId,
                      {
                        companyName,
                        industry,
                        description,
                        foundedYear:
                          Number(foundedYear),
                        employeeCount:
                          Number(employeeCount),
                        location
                      }
                    )

                    setSuccess(
                      'Company updated successfully'
                    )

                    setEditingId(null)

                  } else {

                    await createCompany({
                      companyName,
                      industry,
                      description,
                      foundedYear:
                        Number(foundedYear),
                      employeeCount:
                        Number(employeeCount),
                      location
                    })

                    setSuccess(
                      'Company created successfully'
                    )
                  }

                  setError('')

                  setCompanyName('')
                  setIndustry('')
                  setDescription('')
                  setFoundedYear('')
                  setEmployeeCount('')
                  setLocation('')

                  await loadCompanies()

                } catch (err) {

                  setError(
                    err.response?.data?.message ||
                    'Operation failed'
                  )

                  setSuccess('')
                }
              }}
            >
              {editingId
                ? 'Update Company'
                : 'Create Company'}
            </button>

          </div>

        </div>

        <div className="card shadow-sm mt-4">

          <div className="card-header">
            <h5 className="mb-0">
              All Companies
            </h5>
          </div>

          <div className="card-body">

            {companies.length === 0 ? (
              <p className="text-muted">
                No companies found.
              </p>
            ) : (
              <div className="table-responsive">

                <table className="table table-striped table-hover">

                  <thead className="table-dark">

                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Industry</th>
                      <th>Location</th>
                      <th>Founded</th>
                      <th>Employees</th>
                      <th>Owner</th>
                      <th>Actions</th>
                    </tr>

                  </thead>

                  <tbody>

                    {companies.map((company) => (

                      <tr key={company.id}>

                        <td>{company.id}</td>

                        <td>
                          {company.companyName}
                        </td>

                        <td>
                          {company.industry}
                        </td>

                        <td>
                          {company.location}
                        </td>

                        <td>
                          {company.foundedYear}
                        </td>

                        <td>
                          {company.employeeCount}
                        </td>

                        <td>
                          {company.ownerId}
                        </td>

                        <td>

                          <button
                            className="btn btn-sm btn-warning me-2"
                            onClick={() => {

                              setEditingId(company.id)

                              setCompanyName(
                                company.companyName
                              )

                              setIndustry(
                                company.industry
                              )

                              setDescription(
                                company.description
                              )

                              setFoundedYear(
                                company.foundedYear
                              )

                              setEmployeeCount(
                                company.employeeCount
                              )

                              setLocation(
                                company.location
                              )

                              window.scrollTo({
                                top: 0,
                                behavior: 'smooth'
                              })
                            }}
                          >
                            Edit
                          </button>

                          <button
                            className="btn btn-sm btn-danger"
                            onClick={() =>
                              handleDeleteCompany(
                                company.id
                              )
                            }
                          >
                            Delete
                          </button>

                        </td>

                      </tr>

                    ))}

                  </tbody>

                </table>

              </div>
            )}

          </div>

        </div>

      </div>
    </>
  )
}

export default CompaniesPage