/*export const jsn = (url, data, body) => {
	fetch('http://localhost:8081'+url, body).then(res => {
		return res.json()
	}).then(result => {
		data = result;
	}).catch(e => {
		console.log(e);
	})
}*/

export async function jsnGet (url) {
	const response = await fetch('http://localhost:8081'+url);
	//console.log(response)
	//if(response.ok){
		//const data = response.json();
		//return data;
	//}
	return response.json();
}

export async function jsnPost (url, body) {
	const response = await fetch('http://localhost:8081'+url, {
		method: 'POST',
		body: JSON.stringify(body),
	});
	//if(response.ok){
		//const data = response.json();
		//return data;
	//}
	return response.json();
}

//export default jsn;