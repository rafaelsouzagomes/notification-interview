import React, { useState } from 'react';
import { DataGrid, GridColDef } from '@mui/x-data-grid';

import '../../App.css'
import { useFetchMessages } from '../../hooks/useFetchMessages';
import MessengerLogModal from '../messenger-log-new';
import { getColumnsConfig } from './conlumnsConfig';

const MessengerLog: React.FC = () => {
  const [open, setOpen] = useState(false);
  const { messages, loading, refreshMessages } = useFetchMessages();
  const columns=  getColumnsConfig();

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div className="container">
      <img src="/logo-bg.png" alt="Logo" height="150px" width="150px" />
      {/* <h2 className="title">Messages Log</h2> */}
      <div className="btns">
        <button className="btn-main" onClick={() => setOpen(true)}>New</button>
        <button className="btn-main" onClick={refreshMessages}>Refresh</button>
      </div>
      <div style={{ height: 400, width: 1000 }}>
        <DataGrid
          rows={messages}
          columns={columns}
          pageSize={5}
          getRowId={(row) => row.idLogMessageSent}
          rowsPerPageOptions={[5]}
        />
      </div>
      <MessengerLogModal isOpen={open} setOpen={setOpen} />
    </div>
  );
}

export default MessengerLog;
