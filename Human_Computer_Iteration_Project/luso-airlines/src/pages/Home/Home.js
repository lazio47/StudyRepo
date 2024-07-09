import React, { useState } from 'react';
import { Container, Grid, Typography, Card, CardMedia, CardContent, CardActions, Button } from '@mui/material';
import { Link } from 'react-router-dom';
import { Modal } from '@chakra-ui/react';
import { Box, Flex, Image } from '@chakra-ui/react'; // Importando Box, Flex e Image do Chakra UI
import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import './Home.css';

const Home = () => {
  const [selectedTrip, setSelectedTrip] = useState(null);

  const openModal = (trip) => {
    setSelectedTrip(trip);
  };

  const closeModal = () => {
    setSelectedTrip(null);
  };

  const carouselImages = [
    'https://images.pexels.com/photos/1308659/pexels-photo-1308659.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
    'https://images.pexels.com/photos/19916599/pexels-photo-19916599/free-photo-of-cidade-meio-urbano-ponto-de-referencia-ponto-historico.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
    'https://images.pexels.com/photos/1677358/pexels-photo-1677358.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
    'https://images.pexels.com/photos/1308940/pexels-photo-1308940.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
  ];

  const trips = [
    {
      id: 1,
      title: 'Lisbon to Paris',
      image: 'https://images.pexels.com/photos/1850619/pexels-photo-1850619.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
      price: '99',
      discount: '10%',
      details: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eget velit vel ipsum luctus luctus.'
    },
    {
      id: 2,
      title: 'Rome to Madrid',
      image: 'https://images.pexels.com/photos/3757144/pexels-photo-3757144.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
      price: '129',
      discount: '15%',
      details: 'Suspendisse vel velit at mi volutpat aliquet ut eget purus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae.'
    },
    {
      id: 3,
      title: 'Barcelona to Amsterdam',
      image: 'https://images.pexels.com/photos/1187911/pexels-photo-1187911.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2',
      price: '149',
      discount: '20%',
      details: 'Proin euismod scelerisque magna, ut fringilla libero feugiat non. In hac habitasse platea dictumst.'
    },
  ];

  const carouselSettings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    centerMode: true,
    centerPadding: '25%',
  };

  return (
    <div style={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <div style={{ position: 'relative', height: '400px', overflow: 'hidden', marginBottom: '20px' }}>
        <div style={{ position: 'absolute', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', textAlign: 'center', color: '#fff', zIndex: '1', width: '100%' }}>
          <Typography variant="h3" style={{ fontWeight: 'bold', marginBottom: '10px' }}>Welcome to Luso Airlines</Typography>
          <Typography variant="body1">Your premier destination for affordable air travel.</Typography>
        </div>
        <Slider {...carouselSettings}>
          {carouselImages.map((image, index) => (
            <div key={index} style={{ position: 'relative', height: '100%', width: '100%' }}>
              <img
                src={image}
                alt={`Carousel Image ${index + 1}`}
                style={{ objectFit: 'cover', width: '100%', height: '100%' }}
              />
            </div>
          ))}
        </Slider>
      </div>

      <Container fluid style={{ marginTop: '20px' }}>
        <Grid container spacing={3}>
          {trips.map((trip) => (
            <Grid key={trip.id} item xs={12} md={4}>
              <Card style={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
                <CardMedia
                  component="img"
                  height="200"
                  image={trip.image}
                  alt={trip.title}
                />
                <CardContent style={{ flexGrow: 1 }}>
                  <Typography gutterBottom variant="h5" component="h2">
                    {trip.title}
                  </Typography>
                  <Typography>
                    From {trip.price}€ <span style={{ color: 'red' }}>-{trip.discount}</span>
                  </Typography>
                </CardContent>
                <CardActions>
                  <Link to="/ReserveFlight" style={{ textDecoration: 'none', width: '93%' }}>
                    <Button
                      onClick={() => openModal(trip)}
                      variant="contained"
                      color="primary"
                      fullWidth
                    >
                      Search your Flight
                    </Button>
                  </Link>
                </CardActions>
              </Card>
            </Grid>
          ))}
        </Grid>
      </Container>
      
      <Flex 
  bg="linear-gradient(135deg, #522FCF, #7C3FE6)" 
  color="white" 
  p={20} 
  marginTop="30px" 
  marginBottom="30px" 
  marginLeft="20px"
  marginRight="20px"
  alignItems="center" 
  justifyContent="space-between" 
  position="relative"
  borderRadius="10px"
  boxShadow="0 4px 6px rgba(0, 0, 0, 0.1)"
>
  <Box zIndex="1" maxWidth="60%">
    <Typography variant="h4" fontFamily="Roboto, sans-serif" marginBottom="10px">Special Offers</Typography>
    <Typography variant="body1" fontFamily="Roboto, sans-serif">Check out our latest deals and discounts! Don't miss out!</Typography>
  </Box>
  <Image
    src="https://gallery.yopriceville.com/var/albums/Free-Clipart-Pictures/Airplanes-PNG-Clipart/Transparent_Aircraft_PNG_Vector_Clipart.png?m=1629781590"
    alt="Airplane"
    maxWidth="30%"
    position="absolute"
    right="20px"
    top="45%"
    transform="translateY(-50%)"
  />
</Flex>


      {/* Rodapé */}
      <footer style={{ backgroundColor: '#f0f2f5', padding: '20px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', borderTop: '2px solid black' }}>
        <div>
          <Typography variant="body2" style={{ margin: '0' }}>© 2024 Luso Airlines</Typography>
        </div>
        <div style={{ display: 'flex', justifyContent: 'flex-end', alignItems: 'center' }}>
          <Typography variant="body2" style={{ margin: '0', marginRight: '20px' }}>
            <Link to="/help" className="footer-link">Privacy Policy</Link>
          </Typography>
          <Typography variant="body2" style={{ margin: '0', marginRight: '20px' }}>
            <Link to="/help" className="footer-link">Terms of Service</Link>
          </Typography>
          <Typography variant="body2" style={{ margin: '0', marginRight: '20px' }}>
            <Link to="/help" className="footer-link">Contact Us</Link>
          </Typography>
        </div>
      </footer>

      {/* Modal */}
      <Modal isOpen={selectedTrip !== null} onClose={closeModal}>
        <Modal.Content>
          <Modal.Header>{selectedTrip?.title}</Modal.Header>
          <Modal.Body>
            <Typography>{selectedTrip?.details}</Typography>
          </Modal.Body>
          <Modal.Footer>
            <Button onClick={closeModal}>Close</Button>
          </Modal.Footer>
        </Modal.Content>
      </Modal>
    </div>
  );
};

export default Home;
