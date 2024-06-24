import React from 'react'
import { navigationMenu } from './SidebarNavigation'
import { Divider } from '@mui/material'
import { Avatar, Card } from '@material-ui/core'
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const Sidebar = () => {
  const navigate=useNavigate();
  const { auth } = useSelector(store => store);
  const [anchorEl, setAnchorEl] = React.useState(null);
  const open = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = () => {
    setAnchorEl(null);
  };
  const handleNavigate=(item)=>{
    if(item.title==="Profile"){
      navigate(`/profile/${auth.user?.id}`);
    }
  }
  return (
    <Card className='card h-screen flex flex-col justify-between py-5'>

      <div className='space-y-8 pl-5'>

        <div className=''>
          <span className='logo font-bold text-xl'>Chit Chat choo</span>
        </div>

          <div className='space-y-8'>
             {navigationMenu.map((item)=> <div onClick={()=>handleNavigate(item)} className='cursor-pointer flex space-x-3 items-center'>
              {item.icon}
              <p className='text-xl'>{item.title}</p>
             </div>)}
          </div>

      </div>
      
      <div>
        <Divider/>

        <div className='pl-5 flex items-center justify-between pt-5'>
           <div className='flex items-center space-x-3'>
               <Avatar src='https://img.freepik.com/free-psd/3d-icon-social-media-app_23-2150049569.jpg?t=st=1715528922~exp=1715532522~hmac=c4d20a97689cca1e63d99f5956f287aa30b9a7a110cf85b1722c254ebcee22de&w=826'/>
               <div>
                <p className='font-bold'>{auth.user?.firstName + " " + auth.user?.lastName}</p>
                <p className='opacity-70'>@{auth.user?.firstName?.toLowerCase() + "_" + auth.user?.lastName?.toLowerCase()}</p> 
               </div>
           </div>

           <div><Button
        id="basic-button"
        aria-controls={open ? 'basic-menu' : undefined}
        aria-haspopup="true"
        aria-expanded={open ? 'true' : undefined}
        onClick={handleClick}
      >
        <MoreVertIcon/>
      </Button>
      <Menu
        id="basic-menu"
        anchorEl={anchorEl}
        open={open}
        onClose={handleClose}
        MenuListProps={{
          'aria-labelledby': 'basic-button',
        }}
      >
        <MenuItem onClick={handleClose}>Profile</MenuItem>
        <MenuItem onClick={handleClose}>My account</MenuItem>
        <MenuItem onClick={handleClose}>Logout</MenuItem>
        <MenuItem onClick={handleClose}>Message</MenuItem>
      </Menu></div>
        </div>

      </div>

    </Card>
  )
}

export default Sidebar