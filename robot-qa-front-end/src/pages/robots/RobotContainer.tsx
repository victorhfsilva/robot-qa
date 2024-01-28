import styled from "styled-components";

const RobotContainer = styled.section`
    color: ${props => props.theme.colors.primaryColor};
    font-family: ${props => props.theme.fonts.primaryFont};
    padding: 2% 0% 2% 4%;
    margin: 3%;

    h3 {
        margin-top: 4%;
        font-weight: ${props => props.theme.fontWeight.bold};
        font-size: ${props => props.theme.fontSize.lg};
    }

    h4 {
        margin-top: 4%;
        font-weight: ${props => props.theme.fontWeight.normal};
        font-size: ${props => props.theme.fontSize.base};
    }

    p {
        margin-top: 4%;
        line-height: ${props => props.theme.lineHeight.big};
        font-weight: ${props => props.theme.fontWeight.thin};
        font-size: ${props => props.theme.fontSize.sm};
    }

    li {
        margin-top: 4%;
        margin-left: 8%;
        line-height: ${props => props.theme.lineHeight.normal};
        font-weight: ${props => props.theme.fontWeight.thin};
        font-size: ${props => props.theme.fontSize.xs};
    }
`

export default RobotContainer