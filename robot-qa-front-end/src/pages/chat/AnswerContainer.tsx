import styled from "styled-components";

const AnswerContainer = styled.section`
    color: ${props => props.theme.colors.primaryColor};
    font-family: ${props => props.theme.fonts.primaryFont};
    height: 6rem;

    h3 {
        margin-top: 8%;
        font-weight: ${props => props.theme.fontWeight.normal};
        font-size: ${props => props.theme.fontSize.lg};
    }

    p {
        margin-top: 2%;
        line-height: ${props => props.theme.lineHeight.big};
        font-weight: ${props => props.theme.fontWeight.thin};
        font-size: ${props => props.theme.fontSize.sm};
    }


`

export default AnswerContainer