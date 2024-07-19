import React,  { useEffect } from 'react';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import { LogMessageSent } from '../../models/message-log-sent';
import NovoAviarioModal from './messenger-log-new';
import { findAllLogMessageSent } from '../../services/message-log-sent-service';
import '../../App.css'

const MessengerLog: React.FC = () => {

  const [open, setOpen] = React.useState(false);
  const [messages, setMessages] = React.useState<LogMessageSent[]|[]>([]);

  const columns: GridColDef[] = [
    { 
      field: 'idLogMessageSent', 
      type:'number', 
      headerAlign: 'center',
      align: 'center',   
      headerName: 'ID', 
      flex:1 
    },
    { 
      field: 'message', 
      headerName: 'Message', 
      flex:3,
      headerAlign: 'center',
      align: 'center' 
    },

    
    { 
      field: 'category_name', 
      headerName: 'Category', 
      flex:1,
      headerAlign: 'center',
      align: 'center' 
    },

    { 
      field: 'channel_name', 
      headerName: 'Channel', 
      flex:1,
      headerAlign: 'center',
      align: 'center' 
    },

    { 
      field: 'username_origin', 
      headerName: 'Username Origin', 
      flex:1,
      headerAlign: 'center',
      align: 'center' 
    },

    { 
      field: 'username_destination', 
      headerName: 'Username Destination', 
      flex:1,
      headerAlign: 'center',
      align: 'center' 
    }

  ];

  useEffect(() => {
    console.log('findallLog')
    findAllLogMessageSent({setMessages});
  }, [open])



  return  (    
    <div className="sensor-container" >
      <h2 className="sensor-h2" > Messages Log </h2>
      <button 
        className="sensor-botao" 
        onClick={()=>{  setOpen(true);}}> 
        Send
      </button>
      <div style={{ height: 400, width: 1200 }}>
        <DataGrid
          rows={messages}
          columns={columns}
          pageSize={5}
          getRowId={(r) => r.idLogMessageSent}
          rowsPerPageOptions={[5]}
        />
      </div>
      <NovoAviarioModal 
        isOpen={open} 
        setOpen={setOpen} 
      />
    </div>
  );
}

  export default MessengerLog;


 