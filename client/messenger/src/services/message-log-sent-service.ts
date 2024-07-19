import axios from 'axios';
import { LogMessageSent } from '../models/message-log-sent';
import { Category } from '../models/category';
import { newMessage } from '../models/message-new';

const BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: BASE_URL + '/messages',
});

export const findAllLogMessageSent = () => {
  return api.get<LogMessageSent[]>('/all');
};

export const findAllAllCategories = () => {
  return api.get<Category[]>('category/all');
};

export const saveNewMessage = (newMessage: newMessage) => {
  return api.post('/add', newMessage);
};
