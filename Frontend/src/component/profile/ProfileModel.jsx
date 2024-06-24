import * as React from 'react';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Modal from '@mui/material/Modal';
import { useDispatch } from 'react-redux';
import { useFormik } from 'formik';
import { updateProfileAction } from '../../pages/Authentication/redux/Auth/auth.action';
import { Avatar, IconButton, TextField } from '@mui/material';
import CloseIcon from '@mui/icons-material/Close';

const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 600,
  bgcolor: 'background.paper',
  boxShadow:24,
  p:2,
  outline:'none',
  overflow:'scroll-y',
  borderRadius:3,   
  
};

export default function ProfileModel({open,handleClose }) {
  const dispatch=useDispatch();
  const handleSubmit=(values)=>{
    console.log("values",values)
  }

  const formik=useFormik({
    initialValues:{
        firstName:"",
        lastName:""
    },
    onSubmit:(values,)=>{
        console.log("values",values)
        dispatch(updateProfileAction(values))
    },
  })

  return (
    <div>
      
      <Modal
        open={open}
        onClose={handleClose}
        aria-labelledby="modal-modal-title"
        aria-describedby="modal-modal-description"
      >
        <Box sx={style}>
          <form onSubmit={formik.handleSubmit}>
           <div className='flex items-center justify-between'>
            <div className='flex items-center space-x-3'>
                <IconButton onClick={handleClose}>
                    <CloseIcon/>
                </IconButton>
                 
                 <p>Edit Profile</p>

            </div>

            <Button type='submit'>Save</Button>
           </div>

           <div>
            <div className='h-[15rem]'>
                <img className='w-full h-full rounded-t-md' src='https://img.freepik.com/free-photo/neon-abstract-background-design_191095-446.jpg?t=st=1715798595~exp=1715802195~hmac=8d7f5018455ad97e3f509d91ef49e450d00e7c21742aa24acca9ab1a3654edd5&w=826'/>

            </div>
            <div className='pl-5'>
              <Avatar className='transform -translate-y-24 ' style={{width:"10rem" , height:"10rem"}} src='https://lh3.googleusercontent.com/a/ACg8ocKv89pMoEc4z-r1citYSk7cJTW73IsxA1en0qT8skVeY6c2kh_e=s288-c-no'/>
            </div>
           </div>
           
           <div className='space-y-3'>

            <TextField fullWidth id='firstName' name='firstName' label='First Name' value={formik.values.firstName} onChange={formik.handleChange} />
            <TextField fullWidth id='lastName' name='lastName' label='Last Name' value={formik.values.lastName} onChange={formik.handleChange}/>

           </div>
          </form>
        </Box>
      </Modal>
    </div>
  );
}
