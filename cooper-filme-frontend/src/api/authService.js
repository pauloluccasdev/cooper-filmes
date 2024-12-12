import axios from 'axios';

const loginUser = async (credentials) => {
  try {
    const response = await axios.post('http://localhost:8080/api/auth/login', credentials);
    const token = response.data.token;

    localStorage.setItem('token', token);

    const userResponse = await axios.get('http://localhost:8080/api/auth/me', {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });

    return {
      token,
      role: userResponse.data.role,
    };
  } catch (error) {
    console.error('Erro durante o login:', error);
    throw error;
  }
};

export default loginUser;
