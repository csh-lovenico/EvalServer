package live.bokurano.evalserver.service;

import live.bokurano.evalserver.mapper.AdminMapper;
import live.bokurano.evalserver.mapper.StudentMapper;
import live.bokurano.evalserver.mapper.TeacherMapper;
import live.bokurano.evalserver.model.Admin;
import live.bokurano.evalserver.model.Student;
import live.bokurano.evalserver.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private TeacherMapper teacherMapper;

	@Autowired
	private AdminMapper adminMapper;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Student student = studentMapper.loadStudent(s);
		if (student != null) {
			return new User(student.getStudentId(), student.getStudentPassword(), List.of(new SimpleGrantedAuthority("student")));
		} else {
			Teacher teacher = teacherMapper.loadTeacher(s);
			if (teacher != null) {
				return new User(teacher.getTeacherId(), teacher.getTeacherPassword(), List.of(new SimpleGrantedAuthority("teacher")));
			} else {
				Admin admin = adminMapper.loadAdmin(s);
				if (admin != null) {
					return new User(admin.getAdminUsername(), admin.getAdminPassword(), List.of(new SimpleGrantedAuthority("admin")));
				} else {
					throw new UsernameNotFoundException("Invalid username");
				}
			}
		}
	}
}
