import React , { useEffect, useState }  from 'react';
import Box from "@mui/material/Box";
import Modal from "@mui/material/Modal";
import { Autocomplete, TextField } from '@mui/material';
import { Category } from '../../../models/category';
import { findAllAllCategories, saveNewMessage } from '../../../services/message-log-sent-service';
import { newMessage } from '../../../models/message-new';
import '../../../App.css';

interface TypeAutoComplete {
  label: string |undefined,
  id: number |undefined
}
interface MessageLogNewProps{
  isOpen: boolean,
  setOpen: (open:boolean) => void
}

const NovoAviarioModal: React.FC<MessageLogNewProps> = ({isOpen, setOpen }:MessageLogNewProps) => {

  const [numero, setNumero] =  useState<number|undefined>(undefined);
  const [message, setMessage] = useState<string>('new Message');

  const [idCategory, setIdCategory] = useState<number|null>(null);
  const [categories, setCategories] = useState<Category[] | []>([]);
  const [categorySelected, setCategorySelected] = useState<TypeAutoComplete|null>({id:0, label: '--'});
  const [categoriesOption, setCategoriesOption] = useState<TypeAutoComplete[]|[]>([]);

  useEffect(() => {
    findAllAllCategories({setCategories});    
  }, []);

  useEffect(() => {
    setCategoriesOption([]);
    categories.forEach( (current)=> {
      const option:TypeAutoComplete = {
        label: current.typeCategory,
        id: current.idCategory
      }
      setCategoriesOption(existingItems => {
        return [...existingItems, option]})
    });
  }, [categories]);

  const handleClose = () => {
    setOpen(false);
    cleanFields();
  };

  function cleanFields() {
    setNumero(undefined);
    setMessage('');
    setIdCategory(null);
    setCategorySelected(null);
  }

  function isSaveDisabled(): boolean{
    if( numero === null || idCategory === null || message === '' )
      return true;
    return false;
  }

  function btnSaveStyles(){
    if(isSaveDisabled()){
      return {
        opacity: 0.4,
        cursor:'default',
        marginBottom: 10
      };
    }
    return {
      marginBottom: 10
    }
  }

  function salvar(){
    const newMessage: newMessage = {
      idCategory,
      message
    }
    saveNewMessage(newMessage);
  }

  return (
    <React.Fragment>
      <Modal          
        open={isOpen}
        onClose={handleClose}
        aria-labelledby="child-modal-title"
        aria-describedby="child-modal-description"
      >
        <Box 
          sx={{ ...style, 
                width: 800, 
                height: 700, 
                borderRadius: 2,  
                padding: 4, 
                paddingTop: 0 
              }}
        >
        <h2 className="novoSensorModal-h2" > 001: Send a new Message </h2>
          
        <TextField 
          disabled style={{marginBottom:18}} 
          fullWidth id="outlined-descricao" 
          label="Descrição" 
          variant="outlined"  
          value={message} 
          onChange={(event)=>setMessage(event.target.value)}  
        />

        <Autocomplete
          fullWidth
          id="combo-box-demo"
          options={categoriesOption}
          onChange={(event: any, newValue: any ) => {
            console.log(event);
            setCategorySelected(newValue);
            setIdCategory(newValue?.id);
          }}
          value={categorySelected ||null}
          style={{marginBottom:18}}
          renderInput={(params) =>
             <TextField 
              required {...params}  
              value={categorySelected}
              label="Local Instalado" 
              />}
        />
        <button 
          className="sensor-botao " 
          onClick={salvar} 
          disabled={isSaveDisabled()} 
          style={btnSaveStyles()} >
          Salvar 
        </button>     
        </Box>
      </Modal>
    </React.Fragment>
);
}

export default NovoAviarioModal;

const style = {
  position: "absolute" as const,
  top: "50%",
  left: "50%",
  transform: "translate(-50%, -50%)",
  width: 1000,
  height: 600,
  bgcolor: "background.paper",
  boxShadow: 24
};