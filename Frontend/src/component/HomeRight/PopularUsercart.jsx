import { red } from '@material-ui/core/colors'
import { Avatar, Button, CardHeader, IconButton } from '@mui/material'
import React from 'react'
import MoreVertIcon from '@mui/icons-material/MoreVert';


const PopularUsercart = () => {
  return (
    <div>
         <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <Button color='primary' size='small'>
            Follow
          </Button>
        }
        title="Leo Messi"
        subheader="@leomessi"
      />
    </div>
  )
}

export default PopularUsercart