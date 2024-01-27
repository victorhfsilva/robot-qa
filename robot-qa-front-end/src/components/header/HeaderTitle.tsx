import styled from "styled-components";

const HeaderTitle = styled.h1`
    color: ${props => props.theme.colors.primaryColor};
    font-family: ${props => props.theme.fonts.primaryFont};
    font-weight: ${props => props.theme.fontWeight.bold};
    font-size: ${props => props.theme.fontSize.lg};
    text-align: center;
    padding: 2%;
`

export default HeaderTitle