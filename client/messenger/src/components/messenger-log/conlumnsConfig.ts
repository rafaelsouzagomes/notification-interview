import { GridColDef } from "@mui/x-data-grid";
import dayjs from "dayjs";



const getColumnsConfig = (): GridColDef[] => {
  return [
    { field: 'idLogMessageSent', headerName: 'ID', type: 'number', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'message', headerName: 'Message', flex: 3, headerAlign: 'center', align: 'center' },
    { field: 'category_name', headerName: 'Category', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'channel_name', headerName: 'Channel', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'username_origin', headerName: 'Origin', flex: 1, headerAlign: 'center', align: 'center' },
    { field: 'username_destination', headerName: 'Destination', flex: 1, headerAlign: 'center', align: 'center' },
    {
      field: 'date',
      headerName: 'Date',
      flex: 2,
      headerAlign: 'center',
      align: 'center',
      valueFormatter: (params) => dayjs(params.value).format('DD/MM/YYYY HH:mm:ss')
    }
  ];
}

export {getColumnsConfig};