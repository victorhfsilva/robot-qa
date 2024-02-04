import { Client, Message, StompSubscription } from "@stomp/stompjs"
import { useState } from "react"

export const useWebsocket = () => {
    
    const [message, setMessage] = useState('')

    const getClient = async () : Promise<Client> => {
        return await new Promise<Client> ((resolve, reject) => {
                
            const client = new Client({
                brokerURL: 'ws://localhost:8080/subscribe'
            })

            client.onConnect = () => {
                console.log(`Connected.`)
                resolve(client)
            }
            
            client.onStompError = frame => {
                reject(new Error(frame.body))
                console.error('Broker error: ' + frame.body)
            }

            client.activate()
        })
    }

    const subscribe = (client: Client, robot: string): StompSubscription => {
        return client.subscribe(`/api/v1/robots/${robot}`, (message: Message) => {
            setMessage(message.body)
            console.log(`Subscribed to ${robot}.`)
            console.log(message.body)
        })
    }

    const unsubscribe = (subscription: StompSubscription) =>{
        subscription.unsubscribe()
    }

    const publish = (client: Client, robot: string, question: string) => {
        client.publish({
            destination: `/app/v1/robots/${robot}`,
            body: question
        })
    }


    return {
        message,
        getClient,
        subscribe,
        unsubscribe,
        publish
    }
}