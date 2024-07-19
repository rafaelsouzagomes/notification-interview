import { GridColDef } from "@mui/x-data-grid";



const getColumnsConfig = (): GridColDef[] => {
  return [
    { field: 'idLogMessageSent', headerName: 'ID', type: 'number', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'message', headerName: 'Message', flex: 3, headerAlign: 'center', align: 'center' },
    { field: 'category_name', headerName: 'Category', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'channel_name', headerName: 'Channel', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'username_origin', headerName: 'Username Origin', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'username_destination', headerName: 'Username Destination', flex: 1, headerAlign: 'center', align: 'center' },
  ];
}

export {getColumnsConfig};