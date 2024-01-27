import styled from "styled-components";

const MainContainer = styled.main`
    width: 50%;
    color: ${props => props.theme.colors.primaryColor};
    font-family: ${props => props.theme.fonts.primaryFont};
    padding: 2% 0% 2% 4%;

    h2 {
        font-weight: ${props => props.theme.fontWeight.bold};
        font-size: ${props => props.theme.fontSize.xl};
    }

    p {
        margin-top: 4%;
        line-height: ${props => props.theme.lineHeight.big};
        font-weight: ${props => props.theme.fontWeight.thin};
        font-size: ${props => props.theme.fontSize.base};
    }

`

export default MainContainer