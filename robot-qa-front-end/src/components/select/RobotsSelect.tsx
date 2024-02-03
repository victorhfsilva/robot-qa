import styled from "styled-components";

const RobotsSelect = styled.select`
    margin-top: 4%;    
    padding: 1.5% 10%;
    color: ${props => props.theme.colors.primaryColor};
    background: ${props => props.theme.colors.secundaryBackground};
    font-family: ${props => props.theme.fonts.primaryFont};
    font-weight: ${props => props.theme.fontWeight.thin};
    font-size: ${props => props.theme.fontSize.xs};

    option {
        font-family: ${props => props.theme.fonts.primaryFont};
        font-weight: ${props => props.theme.fontWeight.thin};
        font-size: ${props => props.theme.fontSize.xs};
    }
`

export default RobotsSelect;