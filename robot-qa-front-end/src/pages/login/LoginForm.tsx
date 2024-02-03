import styled from "styled-components";

const LoginForm = styled.form`
    padding-top: 2%;

    label {
        display: flex;
        flex-direction: column;
        margin-top: 6%;
        line-height: ${props => props.theme.lineHeight.big};
        font-weight: ${props => props.theme.fontWeight.thin};
        font-size: ${props => props.theme.fontSize.base};
    }

    select {
        margin-bottom: 4%;
        width: 12rem;
        padding-left: 2%;
    }

    input {
        margin-top: 1%;
        margin-bottom: 2%;
    }
`

export default LoginForm;