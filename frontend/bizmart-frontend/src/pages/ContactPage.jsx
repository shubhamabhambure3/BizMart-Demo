import { useEffect, useState } from 'react'
import Navbar from '../components/Navbar'

import {
  createContact,
  getAllContacts,
  updateContactStatus,
  deleteContact
} from '../services/contactService'

import { formatCurrency }
  from '../utils/currencyUtils'

function ContactPage() {

  const [contacts, setContacts] =
    useState([])

  const [formData, setFormData] =
    useState({
      interestId: '',
      name: '',
      email: '',
      mobile: '',
      message: ''
    })

  const loadContacts =
    async () => {

      try {

        const data =
          await getAllContacts()

        setContacts(data)

      } catch (error) {

        console.error(error)
      }
    }

  useEffect(() => {

    loadContacts()

  }, [])

  const handleChange = (e) => {

    setFormData({
      ...formData,
      [e.target.name]:
        e.target.value
    })
  }

  const handleSubmit =
    async (e) => {

      e.preventDefault()

      try {

        await createContact(
          formData
        )

        setFormData({
          interestId: '',
          name: '',
          email: '',
          mobile: '',
          message: ''
        })

        loadContacts()

      } catch (error) {

        alert(
          error.response?.data?.message ||
          error.message
        )
      }
    }

  const handleStatus =
    async (
      id,
      status
    ) => {

      await updateContactStatus(
        id,
        status
      )

      loadContacts()
    }

  const handleDelete =
    async (id) => {

      await deleteContact(id)

      loadContacts()
    }

  return (

    <>

      <Navbar />
    <div className="container mt-4">
      <h2 className="page-title">
        Contacts
      </h2>

      <form
        onSubmit={
          handleSubmit
        }
        className="mb-4"
      >

        <input
          className="form-control mb-2"
          placeholder="Interest ID"
          name="interestId"
          value={
            formData.interestId
          }
          onChange={
            handleChange
          }
        />

        <input
          className="form-control mb-2"
          placeholder="Name"
          name="name"
          value={
            formData.name
          }
          onChange={
            handleChange
          }
        />

        <input
          className="form-control mb-2"
          placeholder="Email"
          name="email"
          value={
            formData.email
          }
          onChange={
            handleChange
          }
        />

        <input
          className="form-control mb-2"
          placeholder="Mobile"
          name="mobile"
          value={
            formData.mobile
          }
          onChange={
            handleChange
          }
        />

        <textarea
          className="form-control mb-2"
          placeholder="Message"
          name="message"
          value={
            formData.message
          }
          onChange={
            handleChange
          }
        />

        <button
          className="btn btn-primary"
        >
          Create Contact
        </button>

      </form>

      <table
        className="table table-bordered"
      >

        <thead>
          <tr>
            <th>ID</th>
            <th>Related Interest</th>
            <th>Name</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Contact Status</th>
            <th>Actions</th>
          </tr>
        </thead>

        <tbody>

          {contacts.map(
            (contact) => (
              <tr
                key={contact.id}
              >
                <td>
                  {contact.id}
                </td>

                <td>
                  {contact.interestId}
                </td>

                <td>
                  {contact.name}
                </td>

                <td>
                  {contact.email}
                </td>

                <td>
                  {contact.mobile}
                </td>

                <td>
                  {contact.status}
                </td>

                <td>

                  <button
                    className="btn btn-success btn-sm me-2"
                    onClick={() =>
                      handleStatus(
                        contact.id,
                        'CONTACTED'
                      )
                    }
                  >
                    Contacted
                  </button>

                  <button
                    className="btn btn-danger btn-sm"
                    onClick={() =>
                      handleDelete(
                        contact.id
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
    </>
  )
}

export default ContactPage