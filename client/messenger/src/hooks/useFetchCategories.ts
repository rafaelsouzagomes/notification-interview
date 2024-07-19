import { useEffect, useState } from 'react';
import { Category } from '../models/category';
import { findAllAllCategories } from '../services/message-log-sent-service';

export const useFetchCategories = () => {
  const [categories, setCategories] = useState<Category[] | []>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await findAllAllCategories();
        setCategories(response.data);
      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

  return { categories, loading };
};
