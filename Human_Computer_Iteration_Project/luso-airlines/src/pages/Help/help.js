import React from 'react';
import './help.css'; 

function Help() {
  return (
    <div className="App">
      <header className="header">
        <h1>Luso Airlines - Ajuda e Documentação</h1>
      </header>
      <div className="container">
        <section className="section">
          <div className="section-content">
            <h2>Como Reservar um Bilhete</h2>
            <p>Para reservar um bilhete na Luso Airlines, você pode visitar nosso site oficial ou usar nosso aplicativo móvel. No site, basta selecionar o destino desejado, as datas de viagem e o número de passageiros. Em seguida, siga as instruções para concluir o processo de reserva.</p>
          </div>
          <div className="section-image">
            <img src="https://images.pexels.com/photos/2026324/pexels-photo-2026324.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2" alt="Avião" />
          </div>
        </section>
        <section className="section">
          <div className="section-content">
            <h2>Política de Bagagem</h2>
            <p>A política de bagagem da Luso Airlines permite que cada passageiro leve uma mala de mão e despache uma bagagem, sujeita a limites de peso e tamanho. Certifique-se de verificar nossas diretrizes de bagagem antes da sua viagem para evitar taxas extras ou problemas no aeroporto.</p>
          </div>
          <div className="section-image">
            <img src="https://images.pexels.com/photos/17193004/pexels-photo-17193004/free-photo-of-loira-elegancia-sofisticacao-uniforme.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2" alt="Bagagem" />
          </div>
        </section>
        <section className="section">
          <h2>Perguntas Frequentes</h2>
          <ul>
            <li>Como faço para alterar minha reserva?</li>
            <li>Qual é a política de cancelamento da Luso Airlines?</li>
            <li>Como faço para solicitar uma refeição especial durante o voo?</li>
            <li>Quais são as opções de entretenimento a bordo?</li>
            <li>Como posso entrar em contato com o serviço ao cliente?</li>
          </ul>
        </section>
      </div>
    </div>
  );
}

export default Help;
