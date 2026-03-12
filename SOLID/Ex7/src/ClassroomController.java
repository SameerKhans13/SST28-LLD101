public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) {
        this.reg = reg;
    }

    public void startClass() {
        InputConnectable pj = reg.getFirst(InputConnectable.class);
        ((Powerable) pj).powerOn();
        pj.connectInput("HDMI-1");

        BrightnessControllable lights = reg.getFirst(BrightnessControllable.class);
        lights.setBrightness(60);

        TemperatureControllable ac = reg.getFirst(TemperatureControllable.class);
        ac.setTemperatureC(24);

        AttendanceScannable scanner = reg.getFirst(AttendanceScannable.class);
        System.out.println("Attendance scanned: present=" + scanner.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        for (Powerable p : reg.getAll(Powerable.class)) {
            p.powerOff();
        }
    }
}
