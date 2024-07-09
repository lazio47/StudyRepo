import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

// Styled components for CSS styling
const ModalContainer = styled.div`
  position: fixed;
  top: 100px;
  left: 0;
  width: 100%; /* Largura total da tela */
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const FormContainer = styled.form`
  width: fit-content;
  background: #FFFFFF;
  box-shadow: 0px 187px 75px rgba(0, 0, 0, 0.01), 0px 105px 63px rgba(0, 0, 0, 0.05), 0px 47px 47px rgba(0, 0, 0, 0.09), 0px 12px 26px rgba(0, 0, 0, 0.1), 0px 0px 0px rgba(0, 0, 0, 0.1);
  border-radius: 26px;
  max-width: 600px;
  padding: 20px;
`;

const PriceContainer = styled.div`
  width: 100%;
  text-align: center;
  margin-bottom: 20px;
  font-size: 20px;
  font-weight: bold;
  color: #333;
`;

const PaymentOptionsContainer = styled.div`
  width: calc(100% - 40px);
  display: grid;
  grid-template-columns: 33% 34% 33%;
  gap: 20px;
  padding: 10px;
  margin-left: 35px;
`;

const PaymentButton = styled.button`
  height: 55px;
  padding: 0;
  border: 2px solid transparent; /* Adiciona uma borda */
  outline: none;
  background: url(${props => props.background}) no-repeat center center;
  background-size: contain;
  transition: border-color 0.3s; /* Adiciona uma transição para suavizar a mudança de cor da borda */

  &:hover {
    border-color: #ccc; /* Muda a cor da borda ao passar o mouse sobre o botão */
  }
`;

const Separator = styled.div`
  width: calc(100% - 20px);
  display: grid;
  grid-template-columns: 1fr 2fr 1fr;
  gap: 10px;
  color: #8B8E98;
  margin: 0 10px;
`;

const Line = styled.hr`
  display: inline-block;
  width: 100%;
  height: 1px;
  border: 0;
  background-color: #e8e8e8;
  margin: auto;
`;

const SeparatorText = styled.p`
  word-break: keep-all;
  display: block;
  text-align: center;
  font-weight: 600;
  font-size: 11px;
  margin: auto;
`;

const CreditCardFormContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 15px;
`;

const InputContainer = styled.div`
  width: 90%;
  height: fit-content;
  display: flex;
  flex-direction: column;
  gap: 5px;
  margin-left: 30px;
`;

const SplitContainer = styled.div`
  display: grid;
  grid-template-columns: 4fr 2fr;
  gap: 15px;
  margin-right: 40px;
`;

const InputLabel = styled.label`
  font-size: 10px;
  color: #8B8E98;
  font-weight: 600;
`;

const InputField = styled.input`
  width: auto;
  height: 40px;
  padding: 0 0 0 16px;
  border-radius: 9px;
  outline: none;
  background-color: #F2F2F2;
  border: 1px solid #e5e5e500;
  transition: all 0.3s cubic-bezier(0.15, 0.83, 0.66, 1);

  &:focus {
    border: 1px solid transparent;
    box-shadow: 0px 0px 0px 2px #242424;
    background-color: transparent;
  }
`;

const PurchaseButton = styled.button`
  height: 55px;
  border-radius: 11px;
  border: 0;
  outline: none;
  color: #ffffff;
  font-size: 13px;
  font-weight: 700;
  background: linear-gradient(180deg, #363636 0%, #1B1B1B 50%, #000000 100%);
  box-shadow: 0px 0px 0px 0px #FFFFFF, 0px 0px 0px 0px #000000;
  transition: all 0.3s cubic-bezier(0.15, 0.83, 0.66, 1);
  margin-left: 140px;

  &:hover {
    box-shadow: 0px 0px 0px 2px #FFFFFF, 0px 0px 0px 3px #000000;
  }

  &:active {
    background-color: #000000;
  }
`;

// React component
const PaymentForm = () => {
  const navigate = useNavigate();

  const [cardNumber, setCardNumber] = useState('');
  const [expiryDate, setExpiryDate] = useState('');
  const [cvv, setCvv] = useState('');
  const [cardholderName, setCardholderName] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Verificar se todos os campos foram preenchidos
    if (!cardNumber || !expiryDate || !cvv || !cardholderName) {
      toast.error('Por favor, preencha todos os campos.');
      return;
    }

    // Simular o pagamento e redirecionar
    toast.success('Pagamento feito com sucesso!');
    setTimeout(() => {
      navigate('/');
    }, 2000);
  };

  return (
    <>
      <ModalContainer>
        <FormContainer onSubmit={handleSubmit}>
          <PriceContainer>
            Price: 200 EUR
          </PriceContainer>
          <PaymentOptionsContainer>
            {/* Payment options buttons */}
            <PaymentButton style={{ backgroundImage: 'url(https://static.vecteezy.com/system/resources/previews/019/909/676/original/paypal-transparent-paypal-free-free-png.png)', backgroundSize: '110%', backgroundPosition: 'center' }} />
            <PaymentButton style={{ backgroundImage: 'url(https://download.logo.wine/logo/Apple_Pay/Apple_Pay-Logo.wine.png)', backgroundSize: '90%', backgroundPosition: 'bottom', paddingBottom: '15px', paddingTop: "20px"}} />
            <PaymentButton background="https://cdn1.iconfinder.com/data/icons/logos-brands-in-colors/436/Google_Pay_GPay_Logo-512.png" />
          </PaymentOptionsContainer>
          <Separator>
            <Line />
            <SeparatorText>or</SeparatorText>
            <Line />
          </Separator>
          <CreditCardFormContainer>
            <InputContainer>
              <InputLabel>Card Number</InputLabel>
              <InputField type="text" value={cardNumber} onChange={(e) => setCardNumber(e.target.value)} />
            </InputContainer>
            <SplitContainer>
              <InputContainer>
                <InputLabel>Expiry Date</InputLabel>
                <InputField type="text" value={expiryDate} onChange={(e) => setExpiryDate(e.target.value)} />
              </InputContainer>
              <InputContainer>
                <InputLabel>CVV</InputLabel>
                <InputField type="text" value={cvv} onChange={(e) => setCvv(e.target.value)} />
              </InputContainer>
            </SplitContainer>
            <InputContainer>
              <InputLabel>Cardholder Name</InputLabel>
              <InputField type="text" value={cardholderName} onChange={(e) => setCardholderName(e.target.value)} />
            </InputContainer>
            <PurchaseButton type="submit">Purchase</PurchaseButton>
          </CreditCardFormContainer>
        </FormContainer>
      </ModalContainer>
      <ToastContainer />
    </>
  );
};

export default PaymentForm;
