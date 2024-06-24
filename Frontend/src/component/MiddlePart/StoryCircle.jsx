import { Avatar } from '@material-ui/core'
import React from 'react'

const StoryCircle = () => {
  return (
    <div>
        <div className='flex flex-col items-center mr-4 cursor-pointer'>
           <Avatar style={{ width: '5rem', height: '5rem' }} src='https://w0.peakpx.com/wallpaper/205/620/HD-wallpaper-messi-cute-messi-cute-argentina-messi-2021-messi-argentina-messi-2018-messi-argentina-thumbnail.jpg' /> 
         <p>Leo Messi</p>
        </div>
    </div>
  )
}

export default StoryCircle