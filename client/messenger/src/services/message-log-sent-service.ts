import axios from 'axios';
import { LogMessageSent } from '../models/message-log-sent';
import { Category } from '../models/category';
import { newMessage } from '../models/message-new';

const BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: BASE_URL+ '/messages',
});

export interface onGetAllMessagesProps{
  setMessages: (messages: LogMessageSent[] | []) => void,
}
export function findAllLogMessageSent({setMessages}:onGetAllMessagesProps){
  api.get('/all').then(function(response){
    setMessages(response.data);
    console.log(response.data);
  }).catch(function (error) {
    console.log(error);
  });
}

export interface onGetAllCategoriesProps{
  setCategories: (categories: Category[] | []) => void,
}
export function findAllAllCategories({setCategories}:onGetAllCategoriesProps){
  api.get('category/all').then(function(response){
    setCategories(response.data);
  }).catch(function (error) {
    console.log(error);
  });
}

export function saveNewMessage( newMessage: newMessage){
    api.post('/add', {
      message: newMessage.message,
      idCategory: newMessage.idCategory
    })
  .catch(function (error) {
    console.log(error);
  });
}

