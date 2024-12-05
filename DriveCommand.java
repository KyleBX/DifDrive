package robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import robot.drive.Drive;

public class DriveCommand extends CommandBase {
    private final Drive drive;
    private final double leftSpeed;
    private final double rightSpeed;

    public DriveCommand(Drive drive, double leftSpeed, double rightSpeed) {
        this.drive = drive;
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        addRequirements(drive); // Ensure exclusive use of the subsystem
    }

    @Override
    public void execute() {
        drive.createDriveCommand(leftSpeed, rightSpeed).run();
    }

    @Override
    public void end(boolean interrupted) {
        drive.createDriveCommand(0, 0).run(); // Stop the motors
    }

    @Override
    public boolean isFinished() {
        return false; // Run until interrupted
    }
}
