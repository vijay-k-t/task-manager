import {HeroContainer} from '../../shared/components/HeroContainer';
import {SignupForm} from '../components/SignupForm';
import {useState} from 'react';
import {useCreateUserApi} from '../hooks/apiHooks';
// import {Alert} from 'evergreen-ui';

export const SignupPage = () => {
  const [response, setResponse] = useState(null);
  const [error, setError] = useState(null);
  const [signupInput, setSignupInput] = useState(null);
  useCreateUserApi(signupInput, setResponse, setError);

  return (
    <HeroContainer>
      {error && <Alert intent="danger">
        {error.message}
      </Alert>}
      {response && <Alert intent="success">
        Sign up was succesful. You can now log in.
      </Alert>}
      <SignupForm onSubmit={setSignupInput}/>
    </HeroContainer>
  );
}