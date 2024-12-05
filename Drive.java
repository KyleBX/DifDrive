import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import robot.Ports;
import java.util.List;

public class Drive extends SubsystemBase {
    // Motor declarations
    private final CANSparkMax leftLeader = new CANSparkMax(Ports.Drive.LEFT_LEADER, MotorType.kBrushless);
    private final CANSparkMax leftFollower = new CANSparkMax(Ports.Drive.LEFT_FOLLOWER, MotorType.kBrushless);
    private final CANSparkMax rightLeader = new CANSparkMax(Ports.Drive.RIGHT_LEADER, MotorType.kBrushless);
    private final CANSparkMax rightFollower = new CANSparkMax(Ports.Drive.RIGHT_FOLLOWER, MotorType.kBrushless);

    // Constructor for motor configuration
    public Drive() {
        // Reset and configure motors
        for (CANSparkMax spark : List.of(leftLeader, leftFollower, rightLeader, rightFollower)) {
            spark.restoreFactoryDefaults();
            spark.setIdleMode(IdleMode.kBrake);
        }

        // Configure followers
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        // Invert left side to ensure correct forward direction
        leftLeader.setInverted(true);
    }

    // Private drive method to control the motors
    private void drive(double leftSpeed, double rightSpeed) {
        leftLeader.set(leftSpeed);
        rightLeader.set(rightSpeed);
    }

    // Public factory method to create drive commands
    public Runnable createDriveCommand(double leftSpeed, double rightSpeed) {
        return () -> drive(leftSpeed, rightSpeed);
    }
}
