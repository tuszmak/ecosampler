import { Image } from 'antd'
import React from 'react'

export default function NotFound() {
  return (
    <div className='error'>
    <div style={{marginBottom: "30px"}}>Uh oh, this page doesn't exist</div>
    <img src='https://i.imgur.com/HKygOj7.jpg' className='cat' />
    </div>
  )
}
