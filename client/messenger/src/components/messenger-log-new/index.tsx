import React, {  useState } from 'react';
import { Box, Modal, Autocomplete, TextField, Button, Snackbar, Alert} from '@mui/material';
import { useFetchCategories } from '../../hooks/useFetchCategories';
import '../../App.css';
import { saveNewMessage } from '../../services/message-log-sent-service';
import { newMessage } from '../../models/message-new';

interface TypeAutoComplete {
  label: string | undefined;
  id: number | undefined;
}

interface MessageLogNewProps {
  isOpen: boolean;
  setOpen: (open: boolean) => void;
}

const MessengerLogModal: React.FC<MessageLogNewProps> = ({ isOpen, setOpen }) => {
  const [message, setMessage] = useState<string>('new Message');
  const [idCategory, setIdCategory] = useState<number | null>(null);
  const [categorySelected, setCategorySelected] = useState<TypeAutoComplete | null>({ id: 0, label: '--' });

  const { categories, loading } = useFetchCategories();
  const categoriesOption: TypeAutoComplete[] = categories.map(category => ({
    label: category.typeCategory,
    id: category.idCategory
  }));

  const [openSnackbar, setOpenSnackbar] = useState(false);
  const [snackbarMessage, setSnackbarMessage] = useState('');
  const [snackbarSeverity, setSnackbarSeverity] = useState<'success' | 'error'>('success');

  const handleSnackbarClose = () => {
    setOpenSnackbar(false);
  };
  
  const showSnackbar = (message: string, severity: 'success' | 'error') => {
    setSnackbarMessage(message);
    setSnackbarSeverity(severity);
    setOpenSnackbar(true);
  };
  


  const handleClose = () => {
    setOpen(false);
    cleanFields();
  };

  const cleanFields = () => {
    setMessage('');
    setIdCategory(null);
    setCategorySelected(null);
  };

  const isSaveDisabled = (): boolean => {
    return !message || !idCategory;
  };

  const ID_USER_SENDER_ADMIN =1;
  
  const handleSave = async () => {
    if (idCategory && message) {
      const newMsg: newMessage = {
        idCategory,
        message,
        idUser_origin: ID_USER_SENDER_ADMIN
      };
      try {
        await saveNewMessage(newMsg);
        showSnackbar('Message saved successfully!', 'success');
        handleClose();
      } catch (error: any) {
        if (error.response && error.response.data && error.response.data.message) {
          showSnackbar(error.response.data.message, 'error');
        } else {
          showSnackbar('An unexpected error occurred. Please try again.', 'error');
        }
      }
    }
  };

  return (
    <>
    <Modal
      open={isOpen}
      onClose={handleClose}
      aria-labelledby="child-modal-title"
      aria-describedby="child-modal-description"
    >
      <Box sx={style}>
        <h2 className="title-modal">001: Send a new Message</h2>

        <TextField
          fullWidth
          id="outlined-descricao"
          label="Message"
          variant="outlined"
          value={message}
          onChange={(event) => setMessage(event.target.value)}
          style={{ marginBottom: 18 }}
        />

        <Autocomplete
          fullWidth
          id="combo-box-demo"
          options={categoriesOption}
          onChange={(event, newValue) => {
            setCategorySelected(newValue);
            setIdCategory(newValue?.id || null);
          }}
          value={categorySelected}
          style={{ marginBottom: 18 }}
          renderInput={(params) => <TextField {...params} required label="Category" />}
        />

        <Button
          variant="contained"
          color="error"
          onClick={handleSave}
          disabled={isSaveDisabled()}
          style={isSaveDisabled() ? { opacity: 0.4, cursor: 'default', marginBottom: 10 } : { marginBottom: 10 }}
        >
          Save
        </Button>
      </Box>
    </Modal>
        
    <Snackbar
      open={openSnackbar}
      autoHideDuration={6000}
      onClose={handleSnackbarClose}
    >
      <Alert onClose={handleSnackbarClose} 
            severity={snackbarSeverity} 
            sx={{ width: '100%' }}>
        {snackbarMessage}
      </Alert>
    </Snackbar>
    
</>
  );
};

export default MessengerLogModal;

const style = {
  position: 'absolute' as const,
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: '50vw',
  height: '40vh',
  bgcolor: 'background.paper',
  boxShadow: 24,
  borderRadius: 2,
  padding: 4,
  paddingTop: 0
};
