import { useEffect, useState } from "react";
import { RobotModel } from "../../models/robotModel";
import { getRobots } from "../../service/getRobots.service";

function Robot() {

    const [robots, setRobots] = useState<RobotModel[]>([]);
    const [selectedRobot, setSelectedRobot] = useState<string | undefined>(undefined);

    useEffect(() => {
        // Fetch the data when the component mounts
        const fetchRobots = async () => {
          try {
            const data = await getRobots();
            setRobots(data);
          } catch (error) {
            alert('Error fetching robots:' + error);
          }
        };
    
        fetchRobots();
      }, []);

    return (
      <>       
        <Heading 
        align={'center'} 
        as="h3" 
        size={'5'}
        style={{
            margin: '1.2rem 0 0.6rem 0',
            padding: '0.8rem',
        }}>
            Choose a robot:
        </Heading>
        <Flex justify={"center"}>
            <Select.Root 
            size={"3"}
            onValueChange={(value) => setSelectedRobot(value)}
            value={selectedRobot}
            >
                <Select.Trigger />
                <Select.Content>
                    <Select.Group>
                    <Select.Label>Robots</Select.Label>
                        {robots.map((robot) => (
                            <Select.Item key={robot.id} value={robot.name}>
                            {robot.name}
                            </Select.Item>
                        ))}
                    </Select.Group>
                </Select.Content>
            </Select.Root>
        </Flex>
      </>
    )
  }
  
  export default Robot