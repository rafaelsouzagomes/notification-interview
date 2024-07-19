import { useEffect, useState } from 'react';
import { LogMessageSent } from '../models/message-log-sent';
import { findAllLogMessageSent } from '../services/message-log-sent-service';

export const useFetchMessages = () => {
  const [messages, setMessages] = useState<LogMessageSent[] | []>([]);
  const [loading, setLoading] = useState<boolean>(true);

  const fetchMessages = async () => {
    try {
      const response = await findAllLogMessageSent();
      setMessages(response.data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchMessages();
  }, []);

  return { messages, loading, refreshMessages: fetchMessages };
};
