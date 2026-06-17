import { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'

import {
  createFinancial,
  getAllFinancials,
  updateFinancial,
  deleteFinancial
} from '../services/financialService'

import {
  getAllCompanies
} from '../services/companyService'

import { formatCurrency }
  from '../utils/currencyUtils'

function FinancialPage() {

  const [companies, setCompanies] = useState([])
  const [financials, setFinancials] = useState([])

  const [companyId, setCompanyId] = useState('')

  const [revenueYear1, setRevenueYear1] =
    useState('')

  const [revenueYear2, setRevenueYear2] =
    useState('')

  const [revenueYear3, setRevenueYear3] =
    useState('')

  const [ebitdaYear1, setEbitdaYear1] =
    useState('')

  const [ebitdaYear2, setEbitdaYear2] =
    useState('')

  const [ebitdaYear3, setEbitdaYear3] =
    useState('')

  const [assets, setAssets] =
    useState('')

  const [liabilities, setLiabilities] =
    useState('')

  const [editingId, setEditingId] =
    useState(null)

  const [success, setSuccess] =
    useState('')

  const [error, setError] =
    useState('')


  const loadData = async () => {
    try {

      const companyData =
        await getAllCompanies()

      const financialData =
        await getAllFinancials()

      setCompanies(companyData)
      setFinancials(financialData)

    } catch (err) {
      console.error(err)
    }
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


  useEffect(() => {
    getCompanyName()
    loadData()
  }, [])

  const clearForm = () => {

    setCompanyId('')

    setRevenueYear1('')
    setRevenueYear2('')
    setRevenueYear3('')

    setEbitdaYear1('')
    setEbitdaYear2('')
    setEbitdaYear3('')

    setAssets('')
    setLiabilities('')

    setEditingId(null)
  }

  const handleSave = async () => {

    const payload = {

      companyId: Number(companyId),

      revenueYear1,
      revenueYear2,
      revenueYear3,

      ebitdaYear1,
      ebitdaYear2,
      ebitdaYear3,

      assets,
      liabilities
    }

    try {

      if (editingId) {

        await updateFinancial(
          editingId,
          payload
        )

        setSuccess(
          'Financial record updated'
        )

      } else {

        await createFinancial(
          payload
        )

        setSuccess(
          'Financial record created'
        )
      }

      setError('')

      clearForm()

      await loadData()

    } catch (err) {

      setSuccess('')

      setError(
        err.response?.data?.message ||
        'Operation failed - You are not the owner of this company'
      )
    }
  }

  const handleEdit = (financial) => {

    setEditingId(financial.id)

    setCompanyId(financial.companyId)

    setRevenueYear1(financial.revenueYear1)

    setRevenueYear2(financial.revenueYear2)

    setRevenueYear3(financial.revenueYear3)

    setEbitdaYear1(financial.ebitdaYear1)

    setEbitdaYear2(financial.ebitdaYear2)

    setEbitdaYear3(financial.ebitdaYear3)

    setAssets(financial.assets)

    setLiabilities(financial.liabilities)

    window.scrollTo({
      top: 0,
      behavior: 'smooth'
    })
  }

  const handleDelete = async (id) => {

    if (
      !window.confirm(
        'Delete financial record?'
      )
    ) {
      return
    }

    try {

      await deleteFinancial(id)

      setSuccess(
        'Financial record deleted'
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

  return (
    <>
      <Navbar />

      <div className="container mt-4">

        <h1 className="page-title">
          💰 Financial Records
        </h1>

        <div className="card shadow-sm">

          <div className="card-header">
            <h5 className="mb-0">
              Financial Record
            </h5>
          </div>

          <div className="card-body">

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

            <div className="row">

              <div className="col-md-4 mb-3">
                <input
                  className="form-control"
                  placeholder="Revenue Year 1"
                  value={revenueYear1}
                  onChange={(e) =>
                    setRevenueYear1(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
                <input
                  className="form-control"
                  placeholder="Revenue Year 2"
                  value={revenueYear2}
                  onChange={(e) =>
                    setRevenueYear2(
                      e.target.value
                    )
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
                <input
                  className="form-control"
                  placeholder="Revenue Year 3"
                  value={revenueYear3}
                  onChange={(e) =>
                    setRevenueYear3(
                      e.target.value
                    )
                  }
                />
              </div>

            </div>

            <div className="row">

              <div className="col-md-4 mb-3">
                <input
                  className="form-control"
                  placeholder="EBITDA Year 1"
                  value={ebitdaYear1}
                  onChange={(e) =>
                    setEbitdaYear1(e.target.value)
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
                <input
                  className="form-control"
                  placeholder="EBITDA Year 2"
                  value={ebitdaYear2}
                  onChange={(e) =>
                    setEbitdaYear2(e.target.value)
                  }
                />
              </div>

              <div className="col-md-4 mb-3">
                <input
                  className="form-control"
                  placeholder="EBITDA Year 3"
                  value={ebitdaYear3}
                  onChange={(e) =>
                    setEbitdaYear3(e.target.value)
                  }
                />
              </div>

            </div>

            <div className="row">

              <div className="col-md-6 mb-3">
                <input
                  className="form-control"
                  placeholder="Assets"
                  value={assets}
                  onChange={(e) =>
                    setAssets(e.target.value)
                  }
                />
              </div>

              <div className="col-md-6 mb-3">
                <input
                  className="form-control"
                  placeholder="Liabilities"
                  value={liabilities}
                  onChange={(e) =>
                    setLiabilities(e.target.value)
                  }
                />
              </div>

            </div>

            <button
              className="btn btn-primary"
              onClick={handleSave}
            >
              {editingId
                ? 'Update Financial'
                : 'Create Financial'}
            </button>

          </div>

        </div>

        <div className="card mt-4 shadow-sm">

          <div className="card-header">
            <h5 className="mb-0">
              Financial Records
            </h5>
          </div>

          <div className="card-body">

            <div className="table-responsive">

              <table className="table table-bordered">

                <thead className="table-dark">

                  <tr>
                    <th>ID</th>
                    <th>Company</th>
                    <th>Revenue Y1</th>
                    <th>Revenue Y2</th>
                    <th>Revenue Y3</th>

                    <th>EBITDA Y1</th>
                    <th>EBITDA Y2</th>
                    <th>EBITDA Y3</th>

                    <th>Assets</th>
                    <th>Liabilities</th>
                    <th>Actions</th>
                  </tr>

                </thead>

                <tbody>

                  {financials.map(financial => (

                    <tr key={financial.id}>

                      <td>
                        {financial.id}
                      </td>

                      <td>
                        {getCompanyName(financial.companyId)}
                      </td>

                      <td>{formatCurrency(financial.revenueYear1)}</td>
                      <td>{formatCurrency(financial.revenueYear2)}</td>
                      <td>{formatCurrency(financial.revenueYear3)}</td>

                      <td>{formatCurrency(financial.ebitdaYear1)}</td>
                      <td>{formatCurrency(financial.ebitdaYear2)}</td>
                      <td>{formatCurrency(financial.ebitdaYear3)}</td>

                      <td>{formatCurrency(financial.assets)}</td>
                      <td>{formatCurrency(financial.liabilities)}</td>

                      <td>

                        <button
                          className="btn btn-sm btn-warning me-2"
                          onClick={() =>
                            handleEdit(financial)
                          }
                        >
                          Edit
                        </button>

                        <button
                          className="btn btn-sm btn-danger"
                          onClick={() =>
                            handleDelete(
                              financial.id
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

          </div>
        </div>
      </div>
    </>
  )
}

export default FinancialPage