import styled from "styled-components";

const MenuContainer = styled.ul`
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-evenly;
  
    a {
        position: relative;
        cursor: pointer;
        color: ${props => props.theme.colors.primaryColor};
        font-family: ${props => props.theme.fonts.primaryFont};
        font-weight: ${props => props.theme.fontWeight.regular};
        font-size: ${props => props.theme.fontSize.sm};
        list-style-type: none;
        text-decoration: none;

        &:hover {
          text-decoration: underline;
        }
      }
`

export default MenuContainer
