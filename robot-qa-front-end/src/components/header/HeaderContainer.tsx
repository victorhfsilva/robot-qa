import styled from 'styled-components';

const HeaderContainer = styled.header`
  display: grid;
  grid-template-columns: 1fr 5fr;
  align-items: center;
  justify-content: left;
  padding: 2%;
  background-color: ${props => props.theme.colors.primaryBackground};   
`

export default HeaderContainer