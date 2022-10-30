import { useRef } from 'react';
import { Editor as Writer } from '@toast-ui/react-editor';
import prism from 'prismjs';
import 'prismjs/themes/prism.css';

import '@toast-ui/editor/dist/toastui-editor-viewer.css';
import '@toast-ui/editor/dist/toastui-editor.css';
import codeSyntaxHighlight from '@toast-ui/editor-plugin-code-syntax-highlight';
import '@toast-ui/editor-plugin-code-syntax-highlight/dist/toastui-editor-plugin-code-syntax-highlight.css';

function Editor() {
  const editorRef = useRef();

  const handleOnChange = () => {
    const data1 = editorRef.current.getInstance().getHTML();
    const data2 = editorRef.current.getInstance().getMarkdown();
    console.log(data1);
    console.log(data2);
  };

  return (
    <div className="editor-wrapper">
      <Writer
        previewStyle="horizontal"
        height="300px"
        initialEditType="markdown"
        initialValue="## *Your* **markdown** here"
        ref={editorRef}
        plugins={[[codeSyntaxHighlight, { highlighter: prism }]]}
        hideModeSwitch={true}
        onChange={handleOnChange}
      />
    </div>
  );
}

export default Editor;
