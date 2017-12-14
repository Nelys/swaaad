package com.swaaad.controller;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.exolab.castor.types.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.swaaad.dto.HorarioDTO;
import com.swaaad.dto.ResponseDTO;
import com.swaaad.model.Curso;
import com.swaaad.model.Docente;
import com.swaaad.model.Horario;
import com.swaaad.model.Usuario;
import com.swaaad.service.CursoService;
import com.swaaad.service.HorarioService;
import com.swaaad.service.UsuarioService;

@Controller
public class HorarioController {
	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);
	@Autowired
	HorarioService objHorarioService;

	@Autowired
	UsuarioService objUsuarioService;

	@Autowired
	CursoService objCursoService;

	@RequestMapping(value = { "horario" }, method = RequestMethod.GET)
	public ModelAndView listActividadPedagogica(ModelAndView model, HttpServletRequest request) throws Exception {

		logger.info("horario");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		Usuario usuario = objUsuarioService.getUsuarioById(Integer.valueOf(userDetails.getUsername()));
		Docente docente = usuario.getDocentes().get(0);
		String userName = docente.getApellidos() + " ," + docente.getNombre();
		model.addObject("user", userName);

		// obtiene la lista de cursos por docente
		List<Curso> listaCursos = objCursoService.listCursoByDocente(docente.getIdDocente());
		// Guarda la lista de cursos enla variable listaCursos
		model.addObject("listaCursos", listaCursos);
		// se envia a la vista
		model.setViewName("horarios");
		return model;
	}

	@RequestMapping(value = "/saveHorario", method = RequestMethod.POST)
	@ResponseBody
	public ResponseDTO saveHorario(@ModelAttribute HorarioDTO horario) throws Exception {

		logger.info("saveHorario");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		Usuario usuario = objUsuarioService.getUsuarioById(Integer.valueOf(userDetails.getUsername()));
		Docente docente = usuario.getDocentes().get(0);
		String userName = docente.getApellidos() + " ," + docente.getNombre();

		@SuppressWarnings("deprecation")
		Time dtInit = new Time(horario.getHour()[0], horario.getMinute()[0], 00);
		@SuppressWarnings("deprecation")
		Time dtEnd = new Time(horario.getHour()[1], horario.getMinute()[1], 00);
		Curso curso = objCursoService.getCursoById(horario.getCbxCursos());

		if (horario.getCheckbox1() != null) {
			// horario.setDia("Lunes");
			// horario.setHoraInicio(dtInit);
			// horario.setHoraFin(dtEnd);
			// horario.setCurso(curso);
//			objHorarioService.addHorario(horario);
			System.out.println("El registro fue exitoso");
		}
		if (horario.getCheckbox2() != null) {

		}
		if (horario.getCheckbox3() != null) {

		}
		if (horario.getCheckbox4() != null) {

		}
		if (horario.getCheckbox5() != null) {

		}
		if (horario.getCheckbox6() != null) {

		}
		if (horario.getCheckbox7() != null) {

		}
//		try {
//			if (horario.getIdHorario() == 0) {
//				// horario.setDia("Lunes");
//				// horario.setHoraInicio(dtInit);
//				// horario.setHoraFin(dtEnd);
//				// horario.setCurso(curso);
//				objHorarioService.addHorario(horario);
//				System.out.println("El registro fue exitoso");
//
//			} else {
//				objHorarioService.updateHorario(horario);
//				System.out.println("El actualizacion fue exitoso");
//			}
//
//		} catch (
//
//		Exception e) {
//			e.getStackTrace();
//		}

		System.out.println(horario);
		ResponseDTO response = new ResponseDTO();
		response.setMessage("s" + horario.getCbxCursos());

		return response;
	}

	@RequestMapping(value = "/newHorario", method = RequestMethod.GET)
	public ModelAndView newHorario(ModelAndView model) throws Exception {

		logger.info("newCurso");

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		Usuario usuario = objUsuarioService.getUsuarioById(Integer.valueOf(userDetails.getUsername()));
		Docente docente2 = usuario.getDocentes().get(0);
		String userName = docente2.getApellidos() + " ," + docente2.getNombre();
		model.addObject("user", userName);

		model.setViewName("form-horario");
		return model;
	}

}
