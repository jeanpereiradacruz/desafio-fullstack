// components/CustomSnackbar.tsx
import React from 'react';
import { Snackbar, Alert, AlertProps } from '@mui/material';

interface CustomSnackbarProps {
  open: boolean;
  message: string;
  severity: AlertProps['severity']; 
  handleClose: () => void; 
}

const CustomSnackbar: React.FC<CustomSnackbarProps> = ({
  open,
  message,
  severity,
  handleClose,
}) => {
  return (
    <Snackbar
      open={open}
      autoHideDuration={6000} 
      onClose={handleClose}
      anchorOrigin={{ vertical: 'top', horizontal: 'right' }} 
    >
      <Alert onClose={handleClose} severity={severity}>
        {message}
      </Alert>
    </Snackbar>
  );
};

export default CustomSnackbar;
