package lab042;

public class Aircraft {
	private String _name;
	private int _alt;
	private int _speed;
	private String _type;
	private Aircraft _next;
	public Aircraft(){
		_name="";
		_alt=0;
		_speed=0;
		_type="";
		_next=null;
	}

	public Aircraft(String name, int alt, int speed, String type) {
		
		_name=name;
		_alt=alt;
		_speed=speed;
		_type=type;
		_next=null;
		
	}

	
	public Aircraft(String name, int alt, int speed, String type,Aircraft next) {
		
		_name=name;
		_alt=alt;
		_speed=speed;
		_type=type;
		_next=next;
	}
	
	// Copy constructor
	public Aircraft(Aircraft a){
		_name = a._name;
		_type = a._type;
		_alt = a._alt;
		_speed = a._speed;
		_next = null;
	}
	
	public Aircraft get_next(){
		return _next;
	}
	public String get_name() {
		return _name;
	}
	public int get_alt() {
		return _alt;
	}
	public int get_speed() {
		return _speed;
	}
	public String get_type() {
		return _type;
	}
	public void set_next(Aircraft a){
		_next=a;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public void set_alt(int _alt) {
		this._alt = _alt;
	}
	public void set_speed(int _speed) {
		this._speed = _speed;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	@Override
	public String toString(){
		return "Aircraft Name:" + get_name() + ", Altitue:" + get_alt() + ", Speed:" +get_speed() +", Type:" + get_type();
	}

}
